package za.co.app.Userkolekt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtService  implements  IJWTService {

    private final Environment environment;

    public JwtService(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String generateJWT(String subject) {
        return Jwts
                .builder()
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(getSigninkey()) // expiration date.time
                .compact();
    }

    @Override
    public Mono<Boolean> isValidJWT(String jwToken) {
        return Mono.just(jwToken)
                .map( token -> parseToken(token))
                .map(claims -> !claims.getExpiration().before(new Date()))
                .onErrorReturn(false);
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigninkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String extractTokenSubject(String jwToken) {
        return parseToken(jwToken).getSubject();
    }

    private SecretKey getSigninkey() {
        return Optional.ofNullable(environment.getProperty("token.secret"))
                .map( key -> key.getBytes())
                .map(tokenSecret -> Keys.hmacShaKeyFor(tokenSecret))
                .orElseThrow(() -> new IllegalArgumentException("Token secrete not configured in .properties file."));
    }
}

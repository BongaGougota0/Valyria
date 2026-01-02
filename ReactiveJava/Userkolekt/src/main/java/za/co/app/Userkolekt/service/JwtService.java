package za.co.app.Userkolekt.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class JwtService  implements  IJWTService {
    @Override
    public String generateJWT(String subject) {
        return "";
    }

    @Override
    public Mono<Boolean> isValidJWT(String jwToken) {
        return null;
    }

    @Override
    public String extractTokenSubject(String jwToken) {
        return "";
    }
}

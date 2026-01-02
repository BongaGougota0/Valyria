package za.co.app.Userkolekt.service;

import reactor.core.publisher.Mono;

public interface IJWTService {
    String generateJWT(String subject);
    Mono<Boolean> isValidJWT(String jwToken);
    String extractTokenSubject(String jwToken);
}

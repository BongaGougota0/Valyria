package za.co.app.Userkolekt.service;

import reactor.core.publisher.Mono;
import java.util.Map;

public interface IAuthenticationService {
    Mono<Map<String, String>> authenticate(String email, String password);
}

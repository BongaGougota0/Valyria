package za.co.app.Userkolekt.service;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.repository.IUserCrudRepo;
import java.util.Map;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final IUserCrudRepo userRepository;
    private final ReactiveAuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(IUserCrudRepo userRepository,
                                 ReactiveAuthenticationManager authenticationManager,
                                 JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Map<String, String>> authenticate(String email, String password) {
        return null;
    }
}

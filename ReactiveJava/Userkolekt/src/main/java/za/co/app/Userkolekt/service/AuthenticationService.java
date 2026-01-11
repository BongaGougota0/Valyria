package za.co.app.Userkolekt.service;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.UserEntity;
import za.co.app.Userkolekt.repository.IUserCrudRepo;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final IUserCrudRepo userRepository;
    private final ReactiveAuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ReactiveAuthenticationManager reactiveAuthenticationManager;

    public AuthenticationService(IUserCrudRepo userRepository,
                                 ReactiveAuthenticationManager authenticationManager,
                                 JwtService jwtService, ReactiveAuthenticationManager reactiveAuthenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.reactiveAuthenticationManager = reactiveAuthenticationManager;
    }

    @Override
    public Mono<Map<String, String>> authenticate(String email, String password) {
        return reactiveAuthenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password))
                // accept authentication token and create acceptable
                .then(getUserDetails(email))
                .map(this::createAuthResponse);
    }

    private Mono<UserEntity> getUserDetails(String username) {
        return this.userRepository.findUserByEmail(username);
    }

    private Map<String, String> createAuthResponse(UserEntity user) {
        Map<String, String> results = new HashMap<>();
        results.put("user_id", user.getUserId().toString());
        results.put("token", jwtService.generateJWT(user.getUserId().toString()));
        return  results;
    }
}

package za.co.app.Userkolekt.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.LoginCredentials;
import za.co.app.Userkolekt.model.UserEntity;
import za.co.app.Userkolekt.model.UserDto;
import za.co.app.Userkolekt.service.AuthenticationService;
import za.co.app.Userkolekt.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> login(@Validated @RequestBody Mono<LoginCredentials> loginCredentials,
                                              ServerWebExchange serverWebExchange) {
        //  use reactive operators to access the values inside loginCredentials
        return loginCredentials
                .flatMap( authReq ->
                        authenticationService.authenticate(authReq.email(), authReq.password()))
                .map( authResult ->
                    ResponseEntity
                            .ok()
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + authResult.get("token"))
                            .header("UserId", authResult.get("user_id"))
                            .build()
                ).onErrorReturn(BadCredentialsException.class,
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials"))
                .onErrorReturn(Exception.class,
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<UserDto>> registerUser(@Validated @RequestBody Mono<UserEntity> newUser) {
        return userService.createNewUser(newUser).map((userDto) -> ResponseEntity.ok().body(userDto));
    }
}

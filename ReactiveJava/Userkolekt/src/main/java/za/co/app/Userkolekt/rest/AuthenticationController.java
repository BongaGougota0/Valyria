package za.co.app.Userkolekt.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.LoginCredentials;
import za.co.app.Userkolekt.model.User;
import za.co.app.Userkolekt.model.UserDto;
import za.co.app.Userkolekt.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<UserDto>> login(@Validated @RequestBody Mono<LoginCredentials> loginCredentials) {
        return userService.login(loginCredentials)
                .map(obj -> ResponseEntity.status(201).body(obj));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<UserDto>> registerUser(@Validated @RequestBody Mono<User> newUser) {
        return userService.createNewUser(newUser).map((userDto) -> ResponseEntity.ok().body(userDto));
    }
}

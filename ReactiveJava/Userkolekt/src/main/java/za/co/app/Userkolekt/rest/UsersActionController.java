package za.co.app.Userkolekt.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.User;
import za.co.app.Userkolekt.model.UserDto;
import za.co.app.Userkolekt.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Mono<ResponseEntity<UserDto>> createNewUser(@Validated @RequestBody Mono<User> newUser) {
        return userService.createNewuser(newUser)
                .map(obj -> ResponseEntity.status(201).body(obj));
    }
}

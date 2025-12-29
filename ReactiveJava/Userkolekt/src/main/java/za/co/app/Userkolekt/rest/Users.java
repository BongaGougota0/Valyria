package za.co.app.Userkolekt.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.User;

@RestController
@RequestMapping("/users")
public class Users {

    @PostMapping
    public void createNewUser(@Validated @RequestBody Mono<User> newUser) {

    }
}

package za.co.app.Userkolekt.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.UserEntity;
import za.co.app.Userkolekt.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersActionController {
    private final UserService userService;

    public UsersActionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createUserFavorite(@Validated @RequestBody Mono<UserEntity> newUser) {
        return null;
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createUserView(@Validated @RequestBody Mono<UserEntity> newUser) {
        return null;
    }

    @GetMapping("/favourites")
    public ResponseEntity<Flux<String>> getUserFavorites() {
        return null;
    }

    @GetMapping("/views")
    public ResponseEntity<Flux<String>> getUserViews() {
        return null;
    }
}

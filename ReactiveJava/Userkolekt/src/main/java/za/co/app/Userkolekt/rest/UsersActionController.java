package za.co.app.Userkolekt.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.UserAction;
import za.co.app.Userkolekt.service.UsersActionsImpl;


@RestController
@RequestMapping("/users")
public class UsersActionController {

    private final UsersActionsImpl usersActions;

    public UsersActionController(UsersActionsImpl usersActions) {
        this.usersActions = usersActions;
    }

    @PostMapping("/product-favourite")
    public Mono<ResponseEntity<UserAction>> createUserFavorite(@Validated @RequestBody Mono<UserAction> action) {
        return usersActions.favouriteProduct(action).map(actionMono -> ResponseEntity.ok().body(actionMono));
    }

    @PostMapping("/product-view")
    public Mono<ResponseEntity<UserAction>> createUserView(@Validated @RequestBody Mono<UserAction> actionMono) {
        return usersActions.viewProduct(actionMono).map(action -> ResponseEntity.ok().body(action));
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

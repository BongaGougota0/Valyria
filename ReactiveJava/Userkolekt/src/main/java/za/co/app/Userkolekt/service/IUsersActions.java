package za.co.app.Userkolekt.service;

import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.UserAction;

public interface IUsersActions {
    Mono<UserAction> favouriteProduct(Mono<UserAction> actionMono);
    Mono<UserAction> viewProduct(Mono<UserAction> actionMono);
}

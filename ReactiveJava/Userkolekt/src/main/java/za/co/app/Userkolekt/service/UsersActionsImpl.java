package za.co.app.Userkolekt.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.UserAction;
import za.co.app.Userkolekt.repository.UsersActionsRepository;

@Service
public class UsersActionsImpl implements IUsersActions {

    private final UsersActionsRepository actionsRepository;

    public UsersActionsImpl(UsersActionsRepository actionsRepository) {
        this.actionsRepository = actionsRepository;
    }

    @Override
    public Mono<UserAction> favouriteProduct(Mono<UserAction> actionMono) {
        return actionMono.flatMap(action -> actionsRepository.save(action));
    }

    @Override
    public Mono<UserAction> viewProduct(Mono<UserAction> actionMono) {
        return actionMono.flatMap(action -> actionsRepository.save(action));
    }
}

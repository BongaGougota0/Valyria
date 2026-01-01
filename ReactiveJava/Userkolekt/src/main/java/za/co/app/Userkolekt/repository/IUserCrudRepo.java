package za.co.app.Userkolekt.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.User;
import java.util.UUID;

public interface IUserCrudRepo extends ReactiveCrudRepository<User, UUID> {
    Mono<User> findUserByEmail(String email);
    Mono<User> findUserByUserName(String userName);
}

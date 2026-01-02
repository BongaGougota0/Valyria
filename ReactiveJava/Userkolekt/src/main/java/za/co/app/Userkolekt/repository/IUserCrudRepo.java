package za.co.app.Userkolekt.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.UserEntity;
import java.util.UUID;

public interface IUserCrudRepo extends ReactiveCrudRepository<UserEntity, UUID> {
    Mono<UserEntity> findUserByEmail(String email);
    Mono<UserEntity> findUserByUserName(String userName);
}

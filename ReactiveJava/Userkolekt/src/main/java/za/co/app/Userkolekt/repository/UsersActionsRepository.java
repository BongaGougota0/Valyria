package za.co.app.Userkolekt.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import za.co.app.Userkolekt.model.UserAction;

@Repository
public interface UsersActionsRepository extends ReactiveCrudRepository<UserAction, Long> {
}

package za.co.app.Userkolekt.service;

import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.User;
import za.co.app.Userkolekt.model.UserDto;
import java.util.UUID;

public interface IUserService {
    Mono<UserDto> createNewuser(Mono<User> newUser);
    Mono<User> getUserByID(UUID userId);
    Mono<User> getUserByEmail(String email);
    Mono<User> getUserByUsername(String username);
    UserDto entityToDTO(Mono<User> user);
    User DTOtoEntity(UserDto user);
}

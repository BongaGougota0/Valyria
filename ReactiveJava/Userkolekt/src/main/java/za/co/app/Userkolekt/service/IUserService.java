package za.co.app.Userkolekt.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.LoginCredentials;
import za.co.app.Userkolekt.model.UserEntity;
import za.co.app.Userkolekt.model.UserDto;
import java.util.UUID;

public interface IUserService  extends ReactiveUserDetailsService {
    Mono<UserDto> login(Mono<LoginCredentials> loginCredentials);
    Mono<UserDto> createNewUser(Mono<UserEntity> newUser);
    Mono<UserEntity> getUserByID(UUID userId);
    Mono<UserEntity> getUserByEmail(String email);
    Mono<UserEntity> getUserByUsername(String username);
    UserDto entityToDTO(Mono<UserEntity> user);
    UserEntity DTOtoEntity(UserDto user);
}

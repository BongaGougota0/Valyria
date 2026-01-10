package za.co.app.Userkolekt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.LoginCredentials;
import za.co.app.Userkolekt.model.UserDto;
import za.co.app.Userkolekt.model.UserEntity;
import za.co.app.Userkolekt.repository.IUserCrudRepo;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final IUserCrudRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(IUserCrudRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return this.userRepository.findUserByEmail(username)
                .map( userEntity ->
                    // convert user entity to UserDetails,

                    // at this point I had to rename user entity class to UserEntity
                    // User is a reserved class name.
                    User.withUsername(userEntity.getEmail())
                        .password(userEntity.getPassword())
                        .authorities(new ArrayList<>())
                        .build());
    }

    @Override
    public Mono<UserDto> login(Mono<LoginCredentials> loginCredentials) {
        return null;
    }

    @Override
    public Mono<UserDto> createNewUser(Mono<UserEntity> newUser) {
        return newUser
                // Reactive console logging.
                .doOnNext(user -> logger.info("check user creation, username= {}", user.getUserName()))
                .map( user -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    return userRepository.save(user);
                })
                .flatMap(this::entityToDTO);
    }

    @Override
    public Mono<UserEntity> getUserByID(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Mono<UserEntity> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Mono<UserEntity> getUserByUsername(String username) {
        return userRepository.findUserByUserName(username);
    }

    @Override
    public Mono<UserDto> entityToDTO(Mono<UserEntity> user) {
        return user.map( obj -> new UserDto(obj.getUserName(), obj.getEmail()));
    }

    @Override
    public UserEntity DTOtoEntity(UserDto user) {
        return null;
    }
}

package za.co.app.Userkolekt.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.User;
import za.co.app.Userkolekt.model.UserDto;
import za.co.app.Userkolekt.repository.IUserCrudRepo;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final IUserCrudRepo userRepository;

    public UserService(IUserCrudRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDto> createNewuser(Mono<User> newUser) {
        return newUser
                .map(userRepository::save)
                .map(this::entityToDTO);
    }

    @Override
    public Mono<User> getUserByID(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Mono<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Mono<User> getUserByUsername(String username) {
        return userRepository.findUserByUserName(username);
    }

    @Override
    public UserDto entityToDTO(Mono<User> user) {
        return new UserDto(user.block().getEmail(), user.block().getUserName());
    }

    @Override
    public User DTOtoEntity(UserDto user) {
        return null;
    }
}

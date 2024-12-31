package bookstore.services.authservice;

import bookstore.dtos.user.UserRegistrationRequestDto;
import bookstore.dtos.user.UserResponseDto;
import bookstore.exceptions.RegistrationException;
import bookstore.mappers.user.IUserMapper;
import bookstore.models.User;
import bookstore.repositories.userrepository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {
    private final IUserRepository userRepository;

    private final IUserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto) {
        User user = userRepository.getByEmail(userRegistrationRequestDto.getEmail());

        if (user != null) {
            throw new RegistrationException("This email is already taken");
        }

        return userMapper.toResponseDto(
                userRepository.save(
                        userMapper.toEntity(userRegistrationRequestDto)
                )
        );
    }
}

package bookstore.services.authservice;

import bookstore.dtos.user.UserRegistrationRequestDto;
import bookstore.dtos.user.UserResponseDto;
import bookstore.exceptions.RegistrationException;
import bookstore.mappers.user.IUserMapper;
import bookstore.repositories.userrepository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto) {
        boolean isUserExist = userRepository.existsByEmail(userRegistrationRequestDto.getEmail());

        if (isUserExist) {
            throw new RegistrationException("This email is already taken");
        }

        userRegistrationRequestDto.setPassword(
                passwordEncoder.encode(userRegistrationRequestDto.getPassword())
        );

        return userMapper.toResponseDto(
                userRepository.save(
                        userMapper.toEntity(userRegistrationRequestDto)
                )
        );
    }
}

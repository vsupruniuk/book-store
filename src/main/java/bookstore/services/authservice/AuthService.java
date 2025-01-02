package bookstore.services.authservice;

import bookstore.dtos.user.UserLoginRequestDto;
import bookstore.dtos.user.UserLoginResponseDto;
import bookstore.dtos.user.UserRegistrationRequestDto;
import bookstore.dtos.user.UserResponseDto;
import bookstore.exceptions.RegistrationException;
import bookstore.mappers.user.IUserMapper;
import bookstore.repositories.userrepository.IUserRepository;
import bookstore.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

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

    @Override
    public UserLoginResponseDto authenticate(UserLoginRequestDto userLoginRequestDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDto.getEmail(),
                        userLoginRequestDto.getPassword()
                )
        );

        String token = jwtUtil.generateToken(authentication.getName());

        return new UserLoginResponseDto(token);
    }
}

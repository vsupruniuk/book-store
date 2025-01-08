package bookstore.services.authservice;

import bookstore.dtos.user.UserLoginRequestDto;
import bookstore.dtos.user.UserLoginResponseDto;
import bookstore.dtos.user.UserRegistrationRequestDto;
import bookstore.dtos.user.UserResponseDto;
import bookstore.exceptions.RegistrationException;
import bookstore.mappers.shoppingcart.IShoppingCartMapper;
import bookstore.mappers.user.IUserMapper;
import bookstore.models.ShoppingCart;
import bookstore.models.User;
import bookstore.repositories.shoppingcartrepository.IShoppingCartRepository;
import bookstore.repositories.userrepository.IUserRepository;
import bookstore.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {
    private final IUserRepository userRepository;
    private final IShoppingCartRepository shoppingCartRepository;

    private final IShoppingCartMapper shoppingCartMapper;
    private final IUserMapper userMapper;

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto) {
        if (userRepository.existsByEmail(userRegistrationRequestDto.getEmail())) {
            throw new RegistrationException("This email is already taken");
        }

        userRegistrationRequestDto.setPassword(
                passwordEncoder.encode(userRegistrationRequestDto.getPassword())
        );

        User user = userRepository.save(
                userMapper.toEntity(userRegistrationRequestDto)
        );

        ShoppingCart shoppingCart = shoppingCartMapper.toEntity(user);

        shoppingCartRepository.save(shoppingCart);

        return userMapper.toResponseDto(user);
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

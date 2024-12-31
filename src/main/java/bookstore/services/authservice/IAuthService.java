package bookstore.services.authservice;

import bookstore.dtos.user.UserRegistrationRequestDto;
import bookstore.dtos.user.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface IAuthService {
    UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto);
}

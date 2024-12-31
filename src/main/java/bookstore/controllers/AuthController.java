package bookstore.controllers;

import bookstore.dtos.user.UserRegistrationRequestDto;
import bookstore.dtos.user.UserResponseDto;
import bookstore.services.authservice.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authorization endpoints")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IAuthService authService;

    @Operation(description = "Register in the app")
    @PostMapping("/registration")
    public UserResponseDto registration(
            @RequestBody @Valid UserRegistrationRequestDto userRegistrationRequestDto
    ) {
        return authService.register(userRegistrationRequestDto);
    }
}

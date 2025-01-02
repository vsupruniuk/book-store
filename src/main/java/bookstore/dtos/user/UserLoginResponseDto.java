package bookstore.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserLoginResponseDto {
    private String token;
}

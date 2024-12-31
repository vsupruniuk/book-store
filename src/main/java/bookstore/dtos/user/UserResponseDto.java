package bookstore.dtos.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String shippingAddress;
}

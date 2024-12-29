package bookstore.dtos.user;

import bookstore.annotations.fieldmatch.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@FieldMatch(
        source = "password",
        target = "repeatPassword",
        message = "password and repeatPassword must match"
)
public class UserRegistrationRequestDto {
    @Email(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "email must be a valid email address"
    )
    @Length(max = 255, message = "email length must be between 1 and 255 characters")
    private String email;

    @NotBlank(message = "password must be provided")
    @Length(min = 6, max = 255, message = "password length must be between 6 and 255 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[\\d\\W]).+$",
            message = "password must contains at lease 1 uppercase letter "
                    + "and 1 special character or number"
    )
    private String password;

    private String repeatPassword;

    @NotBlank(message = "firstName must be provided")
    @Length(max = 255, message = "firstName length must be between 1 and 255 characters")
    private String firstName;

    @NotBlank(message = "lastName must be provided")
    @Length(max = 255, message = "lastName length must be between 1 and 255 characters")
    private String lastName;

    @Length(
            min = 1,
            max = 255,
            message = "shippingAddress length must be between 1 and 255 characters"
    )
    private String shippingAddress;
}

package bookstore.dtos.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateUpdateCategoryRequestDto {
    @NotBlank(message = "name must be provided")
    @Length(max = 255, message = "name; length must be between 1 and 255 characters")
    private String name;

    @Length(min = 1, max = 500, message = "description length must be between 1 and 500 characters")
    private String description;
}

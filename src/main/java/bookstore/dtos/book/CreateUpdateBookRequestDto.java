package bookstore.dtos.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateUpdateBookRequestDto {
    @NotBlank(message = "title must be provided")
    @Length(max = 255, message = "title length must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "author must be provided")
    @Length(max = 255, message = "author length must be between 1 and 255 characters")
    private String author;

    @NotBlank(message = "isbn must be provided")
    @Length(max = 255, message = "isbn length must be between 1 and 255 characters")
    @Pattern(
            regexp = "^(97(8|9))?\\d{9}(\\d|X)$",
            message = "isbn must be a valid ISBN-10 or ISBN-13 code"
    )
    private String isbn;

    @NotNull(message = "price must be provided")
    @Min(value = 0, message = "price must be greater than 0")
    private BigDecimal price;

    @Length(min = 1, max = 255, message = "description length must be between 1 and 255 characters")
    private String description;

    @Length(min = 1, max = 255, message = "coverImage length must be between 1 and 255 characters")
    private String coverImage;

    @NotEmpty(message = "At lease 1 category must be provided")
    private List<Long> categories;
}

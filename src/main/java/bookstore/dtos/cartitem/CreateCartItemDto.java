package bookstore.dtos.cartitem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCartItemDto {
    @NotNull(message = "bookId must be provided")
    @Min(value = 1, message = "bookId cannot be less than 1")
    private Long bookId;

    @NotNull(message = "quantity must be provided")
    @Min(value = 1, message = "quantity cannot be less than 1")
    private Integer quantity;
}

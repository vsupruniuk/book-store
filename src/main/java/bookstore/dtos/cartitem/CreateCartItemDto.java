package bookstore.dtos.cartitem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateCartItemDto {
    @NotNull(message = "bookId must be provided")
    @Positive(message = "bookId cannot be less than 1")
    private Long bookId;

    @NotNull(message = "quantity must be provided")
    @Positive(message = "quantity cannot be less than 1")
    private Integer quantity;
}

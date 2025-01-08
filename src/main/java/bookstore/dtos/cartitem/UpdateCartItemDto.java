package bookstore.dtos.cartitem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemDto {
    @NotNull(message = "quantity must be provided")
    @Min(value = 1, message = "quantity cannot be less than 1")
    private Integer quantity;
}

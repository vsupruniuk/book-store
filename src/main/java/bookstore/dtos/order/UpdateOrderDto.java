package bookstore.dtos.order;

import bookstore.models.Order;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderDto {
    @NotNull(message = "status must be provided")
    private Order.Status status;
}

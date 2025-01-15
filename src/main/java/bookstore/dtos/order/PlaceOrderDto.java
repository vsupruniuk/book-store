package bookstore.dtos.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PlaceOrderDto {
    @NotBlank(message = "shippingAddress must be provided")
    @Length(max = 255, message = "shippingAddress length must be between 1 and 255 characters")
    private String shippingAddress;
}

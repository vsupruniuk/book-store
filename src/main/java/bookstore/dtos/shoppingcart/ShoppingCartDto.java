package bookstore.dtos.shoppingcart;

import bookstore.dtos.cartitem.CartItemWithBookTitleDto;
import java.util.List;
import lombok.Data;

@Data
public class ShoppingCartDto {
    private Long id;

    private Long userId;

    private List<CartItemWithBookTitleDto> cartItems;
}

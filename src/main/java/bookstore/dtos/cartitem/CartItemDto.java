package bookstore.dtos.cartitem;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;

    private Long shoppingCartId;

    private Long bookId;

    private Integer quantity;
}

package bookstore.dtos.shoppingcart;

import bookstore.models.User;
import lombok.Data;

@Data
public class ShoppingCartWithoutItemIdsDto {
    private Long id;

    private User user;
}

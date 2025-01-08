package bookstore.services.shoppingcartservice;

import bookstore.dtos.shoppingcart.ShoppingCartDto;
import bookstore.dtos.shoppingcart.ShoppingCartWithoutItemIdsDto;

public interface IShoppingCartService {
    ShoppingCartDto getByUserIdWithCartItems(Long userId);

    ShoppingCartWithoutItemIdsDto getByUserId(Long userId);
}

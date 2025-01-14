package bookstore.services.cartitem;

import bookstore.dtos.cartitem.CartItemDto;
import bookstore.dtos.cartitem.CreateCartItemDto;
import bookstore.dtos.cartitem.UpdateCartItemDto;

public interface ICartItemService {
    CartItemDto addCartItem(
            Long userId,
            CreateCartItemDto createUpdateCartItemDto
    );

    CartItemDto updateCartItem(
            Long cartItemId,
            UpdateCartItemDto updateCartItemDto
    );

    void deleteCartItem(Long cartItemId);
}

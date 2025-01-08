package bookstore.services.cartitemservice;

import bookstore.dtos.cartitem.CartItemDto;
import bookstore.dtos.cartitem.CreateCartItemDto;
import bookstore.dtos.cartitem.UpdateCartItemDto;
import bookstore.dtos.shoppingcart.ShoppingCartWithoutItemIdsDto;
import bookstore.exceptions.EntityNotFoundException;
import bookstore.mappers.cartitem.ICartItemMapper;
import bookstore.models.CartItem;
import bookstore.repositories.bookrepository.IBookRepository;
import bookstore.repositories.cartitemrepository.ICartItemRepository;
import bookstore.services.shoppingcartservice.IShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartItemService implements ICartItemService {
    private final IBookRepository bookRepository;
    private final ICartItemRepository cartItemRepository;

    private final ICartItemMapper cartItemMapper;
    private final IShoppingCartService shoppingCartService;

    @Override
    public CartItemDto addCartItem(
            Long userId,
            CreateCartItemDto createUpdateCartItemDto
    ) {
        if (!bookRepository.existsById(createUpdateCartItemDto.getBookId())) {
            throw new EntityNotFoundException(
                    "Book with id %d does not exist".formatted(createUpdateCartItemDto.getBookId())
            );
        }

        ShoppingCartWithoutItemIdsDto shoppingCart = shoppingCartService.getByUserId(userId);

        CartItem cartItem = cartItemMapper.toEntity(createUpdateCartItemDto, shoppingCart);

        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public CartItemDto updateCartItem(
            Long cartItemId,
            UpdateCartItemDto updateCartItemDto
    ) {
        CartItem cartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cart item with id %d does not exist".formatted(cartItemId)
                ));

        cartItemMapper.updateCartItemFromDto(cartItem, updateCartItemDto);

        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new EntityNotFoundException(
                    "Cart item with id %d does not exist".formatted(cartItemId)
            );
        }

        cartItemRepository.deleteById(cartItemId);
    }
}

package bookstore.services.shoppingcartservice;

import bookstore.dtos.shoppingcart.ShoppingCartDto;
import bookstore.dtos.shoppingcart.ShoppingCartWithoutItemIdsDto;
import bookstore.exceptions.EntityNotFoundException;
import bookstore.mappers.shoppingcart.IShoppingCartMapper;
import bookstore.repositories.shoppingcartrepository.IShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShoppingCartService implements IShoppingCartService {
    private final IShoppingCartRepository shoppingCartRepository;
    private final IShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartDto getByUserIdWithCartItems(Long userId) {
        return shoppingCartMapper.toDto(
                shoppingCartRepository
                        .findByUserIdWithCartItems(userId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Shopping cart for user with id %d does not exist".formatted(userId)
                        ))
        );
    }

    @Override
    public ShoppingCartWithoutItemIdsDto getByUserId(Long userId) {
        return shoppingCartRepository
                .findByUserId(userId)
                .map(shoppingCartMapper::toShoppingCartWithoutItemIds)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Shopping cart with user id %d does not exist".formatted(userId)
                ));
    }
}

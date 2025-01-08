package bookstore.mappers.shoppingcart;

import bookstore.config.MapperConfig;
import bookstore.dtos.shoppingcart.ShoppingCartDto;
import bookstore.dtos.shoppingcart.ShoppingCartWithoutItemIdsDto;
import bookstore.mappers.cartitem.ICartItemMapper;
import bookstore.models.ShoppingCart;
import bookstore.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = ICartItemMapper.class)
public interface IShoppingCartMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    ShoppingCart toEntity(User user);

    ShoppingCartWithoutItemIdsDto toShoppingCartWithoutItemIds(ShoppingCart shoppingCart);

    @Mapping(target = "userId", source = "shoppingCart.user.id")
    @Mapping(target = "cartItems", source = "cartItems", qualifiedByName = "mapToCartItemsWithBook")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);
}

package bookstore.mappers.cartitem;

import bookstore.config.MapperConfig;
import bookstore.dtos.cartitem.CartItemDto;
import bookstore.dtos.cartitem.CartItemWithBookTitleDto;
import bookstore.dtos.cartitem.CreateCartItemDto;
import bookstore.dtos.cartitem.UpdateCartItemDto;
import bookstore.dtos.shoppingcart.ShoppingCartWithoutItemIdsDto;
import bookstore.mappers.book.IBookMapper;
import bookstore.models.CartItem;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = IBookMapper.class)
public interface ICartItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shoppingCart", source = "shoppingCart")
    @Mapping(
            target = "book",
            source = "createUpdateCartItemDto.bookId",
            qualifiedByName = "bookFromId"
    )
    CartItem toEntity(
            CreateCartItemDto createUpdateCartItemDto,
            ShoppingCartWithoutItemIdsDto shoppingCart
    );

    @Mapping(target = "shoppingCartId", source = "cartItem.shoppingCart.id")
    @Mapping(target = "bookId", source = "cartItem.book.id")
    CartItemDto toDto(CartItem cartItem);

    @Mapping(target = "bookId", source = "cartItem.book.id")
    @Mapping(target = "bookTitle", source = "cartItem.book.title")
    CartItemWithBookTitleDto toCartItemWithBookTitleDto(CartItem cartItem);

    void updateCartItemFromDto(
            @MappingTarget CartItem cartItem,
            UpdateCartItemDto updateCartItemDto
    );

    @Named("mapToCartItemsWithBook")
    default List<CartItemWithBookTitleDto> mapToCartItemsWithBook(List<CartItem> cartItems) {
        return cartItems
                .stream()
                .map(this::toCartItemWithBookTitleDto)
                .toList();
    }
}

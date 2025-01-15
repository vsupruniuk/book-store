package bookstore.mappers.orderitem;

import bookstore.config.MapperConfig;
import bookstore.dtos.orderitems.OrderItemDto;
import bookstore.models.CartItem;
import bookstore.models.OrderItem;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface IOrderItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "price", source = "cartItem.book.price")
    OrderItem toEntityFromCartItem(CartItem cartItem);

    @Mapping(target = "bookId", source = "book.id")
    OrderItemDto toDto(OrderItem orderItem);

    @Named("toOrderItemDtoList")
    default List<OrderItemDto> toOrderItemDtoList(List<OrderItem> orderItems) {
        return orderItems
                .stream()
                .map(this::toDto)
                .toList();
    }
}

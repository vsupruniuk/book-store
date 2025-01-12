package bookstore.mappers.order;

import bookstore.config.MapperConfig;
import bookstore.dtos.order.OrderDto;
import bookstore.mappers.orderitem.IOrderItemMapper;
import bookstore.models.Order;
import bookstore.models.OrderItem;
import bookstore.models.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = IOrderItemMapper.class)
public interface IOrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "shippingAddress", source = "shippingAddress")
    Order toEntity(User user, List<OrderItem> orderItems, String shippingAddress);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "orderItems", source = "orderItems", qualifiedByName = "toOrderItemDtoList")
    OrderDto toDto(Order order);

    @AfterMapping
    default void setStatus(@MappingTarget Order order) {
        order.setStatus(Order.Status.NEW);
    }

    @AfterMapping
    default void setTotal(@MappingTarget Order order) {
        order.setTotal(
                order
                        .getOrderItems()
                        .stream()
                        .map(orderItem -> orderItem
                                .getPrice()
                                .multiply(
                                        BigDecimal.valueOf(
                                                orderItem.getQuantity()
                                        )
                                ))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }

    @AfterMapping
    default void setOrderDate(@MappingTarget Order order) {
        order.setOrderDate(LocalDateTime.now());
    }

    @AfterMapping
    default void setOrderToOrderItems(@MappingTarget Order order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
        }
    }
}

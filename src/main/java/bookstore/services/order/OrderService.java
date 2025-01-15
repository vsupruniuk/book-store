package bookstore.services.order;

import bookstore.dtos.order.OrderDto;
import bookstore.dtos.order.PlaceOrderDto;
import bookstore.dtos.order.UpdateOrderDto;
import bookstore.exceptions.EntityNotFoundException;
import bookstore.mappers.order.IOrderMapper;
import bookstore.mappers.orderitem.IOrderItemMapper;
import bookstore.models.CartItem;
import bookstore.models.Order;
import bookstore.models.OrderItem;
import bookstore.models.ShoppingCart;
import bookstore.models.User;
import bookstore.repositories.cartitem.ICartItemRepository;
import bookstore.repositories.order.IOrderRepository;
import bookstore.repositories.shoppingcart.IShoppingCartRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {
    private final ICartItemRepository cartItemRepository;
    private final IShoppingCartRepository shoppingCartRepository;
    private final IOrderRepository orderRepository;

    private final IOrderMapper orderMapper;
    private final IOrderItemMapper orderItemMapper;

    @Override
    public Page<OrderDto> getUserOrders(Long userID, Pageable pageable) {
        return orderRepository
                .getAllByUserId(userID, pageable)
                .map(orderMapper::toDto);
    }

    @Override
    @Transactional
    public OrderDto placeOrder(User user, PlaceOrderDto placeOrderDto) {
        ShoppingCart shoppingCart = shoppingCartRepository
                .findByUserIdWithCartItems(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Shopping cart for this user does not exist"
                ));

        List<OrderItem> orderItems = shoppingCart
                .getCartItems()
                .stream()
                .map(orderItemMapper::toEntityFromCartItem)
                .toList();

        Order order = orderRepository.save(
                orderMapper.toEntity(
                        user,
                        orderItems,
                        placeOrderDto.getShippingAddress()
                )
        );

        cartItemRepository
                .softDeleteAllById(
                        shoppingCart
                                .getCartItems()
                                .stream()
                                .map(CartItem::getId)
                                .toList()
        );

        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto updateOrderStatus(Long id, UpdateOrderDto updateOrderDto) {
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Order with id %d does not exist".formatted(id)
                ));

        order.setStatus(updateOrderDto.getStatus());

        return orderMapper.toDto(orderRepository.save(order));
    }
}

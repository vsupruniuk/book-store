package bookstore.services.orderservice;

import bookstore.dtos.order.OrderDto;
import bookstore.dtos.order.PlaceOrderDto;
import bookstore.dtos.order.UpdateOrderDto;
import bookstore.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    Page<OrderDto> getUserOrders(Long userID, Pageable pageable);

    OrderDto placeOrder(User user, PlaceOrderDto placeOrderDto);

    OrderDto updateOrderStatus(Long id, UpdateOrderDto updateOrderDto);
}

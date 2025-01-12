package bookstore.services.orderitemservice;

import bookstore.dtos.orderitems.OrderItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderItemService {
    Page<OrderItemDto> getAllByOrderId(Long orderId, Pageable pageable);

    OrderItemDto getById(Long id);
}

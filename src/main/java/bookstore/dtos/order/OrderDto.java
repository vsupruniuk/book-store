package bookstore.dtos.order;

import bookstore.dtos.orderitems.OrderItemDto;
import bookstore.models.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderDto {
    private Long id;

    private Long userId;

    private List<OrderItemDto> orderItems;

    private LocalDateTime orderDate;

    private BigDecimal total;

    private Order.Status status;
}

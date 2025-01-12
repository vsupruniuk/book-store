package bookstore.dtos.orderitems;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;

    private Long bookId;

    private Integer quantity;
}

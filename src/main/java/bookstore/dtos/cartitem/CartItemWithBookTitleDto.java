package bookstore.dtos.cartitem;

import lombok.Data;

@Data
public class CartItemWithBookTitleDto {
    private Long id;

    private Long bookId;

    private String bookTitle;

    private Integer quantity;
}

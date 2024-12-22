package bookstore.dtos.book;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateUpdateBookRequestDto {
    private String title;

    private String author;

    private String isbn;

    private BigDecimal price;

    private String description;

    private String coverImage;
}

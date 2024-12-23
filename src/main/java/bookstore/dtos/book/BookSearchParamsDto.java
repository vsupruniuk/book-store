package bookstore.dtos.book;

import lombok.Data;

@Data
public class BookSearchParamsDto {
    private String title;

    private String author;

    private String isbn;
}

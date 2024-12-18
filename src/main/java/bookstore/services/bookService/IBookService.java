package bookstore.services.bookService;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.CreateBookRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);
}

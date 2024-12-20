package bookstore.services.bookservice;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IBookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);
}

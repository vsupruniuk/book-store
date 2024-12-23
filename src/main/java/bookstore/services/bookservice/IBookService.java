package bookstore.services.bookservice;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.BookSearchParamsDto;
import bookstore.dtos.book.CreateUpdateBookRequestDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IBookService {
    BookDto save(CreateUpdateBookRequestDto createUpdateBookRequestDto);

    List<BookDto> findAll();

    List<BookDto> search(BookSearchParamsDto bookSearchParamsDto);

    BookDto findById(Long id);

    BookDto update(Long id, CreateUpdateBookRequestDto createUpdateBookRequestDto);

    void delete(Long id);
}

package bookstore.services.bookservice;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.BookSearchParamsDto;
import bookstore.dtos.book.CreateUpdateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IBookService {
    BookDto save(CreateUpdateBookRequestDto createUpdateBookRequestDto);

    List<BookDto> findAll(Pageable pageable);

    List<BookDto> search(Pageable pageable, BookSearchParamsDto bookSearchParamsDto);

    BookDto findById(Long id);

    BookDto update(Long id, CreateUpdateBookRequestDto createUpdateBookRequestDto);

    void delete(Long id);
}

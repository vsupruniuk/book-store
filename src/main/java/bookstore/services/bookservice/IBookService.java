package bookstore.services.bookservice;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.BookDtoWithoutCategoryIds;
import bookstore.dtos.book.BookSearchParamsDto;
import bookstore.dtos.book.CreateUpdateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IBookService {
    BookDto save(CreateUpdateBookRequestDto createUpdateBookRequestDto);

    Page<BookDto> findAll(Pageable pageable);

    Page<BookDtoWithoutCategoryIds> findAllByCategoryId(Pageable pageable, Long id);

    Page<BookDto> search(Pageable pageable, BookSearchParamsDto bookSearchParamsDto);

    BookDto findById(Long id);

    BookDto update(Long id, CreateUpdateBookRequestDto createUpdateBookRequestDto);

    void delete(Long id);
}

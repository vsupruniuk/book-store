package bookstore.services.bookservice;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.BookSearchParamsDto;
import bookstore.dtos.book.CreateUpdateBookRequestDto;
import bookstore.exceptions.EntityNotFoundException;
import bookstore.mappers.book.IBookMapper;
import bookstore.models.Book;
import bookstore.repositories.ISpecificationBuilder;
import bookstore.repositories.bookrepository.IBookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService implements IBookService {
    private final IBookRepository bookRepository;
    private final ISpecificationBuilder<Book> bookSpecificationBuilder;

    private final IBookMapper bookMapper;

    @Override
    public BookDto save(CreateUpdateBookRequestDto createUpdateBookRequestDto) {
        return bookMapper.toDto(
                bookRepository.save(
                        bookMapper.toEntity(createUpdateBookRequestDto)
                )
        );
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository
                .findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDto> search(BookSearchParamsDto bookSearchParamsDto) {
        Specification<Book> specification = bookSpecificationBuilder.build(bookSearchParamsDto);

        return bookRepository
                .findAll(specification)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookRepository
                .findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Entity with id %d not found".formatted(id)
                ));
    }

    @Override
    public BookDto update(Long id, CreateUpdateBookRequestDto createUpdateBookRequestDto) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Entity with id %d not found".formatted(id)
                ));

        bookMapper.updateBookFromDto(book, createUpdateBookRequestDto);

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        findById(id);

        bookRepository.deleteById(id);
    }
}

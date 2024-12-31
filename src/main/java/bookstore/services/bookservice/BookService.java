package bookstore.services.bookservice;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.BookSearchParamsDto;
import bookstore.dtos.book.CreateUpdateBookRequestDto;
import bookstore.exceptions.EntityNotFoundException;
import bookstore.exceptions.IsbnConflictException;
import bookstore.mappers.book.IBookMapper;
import bookstore.models.Book;
import bookstore.repositories.ISpecificationBuilder;
import bookstore.repositories.bookrepository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Book book = bookRepository.findByIsbn(createUpdateBookRequestDto.getIsbn());

        if (book != null) {
            throw new IsbnConflictException(
                    "Book with ISBN %s already exist"
                            .formatted(createUpdateBookRequestDto.getIsbn())
            );
        }

        return bookMapper.toDto(
                bookRepository.save(
                        bookMapper.toEntity(createUpdateBookRequestDto)
                )
        );
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository
                .findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public Page<BookDto> search(Pageable pageable, BookSearchParamsDto bookSearchParamsDto) {
        Specification<Book> specification = bookSpecificationBuilder.build(bookSearchParamsDto);

        return bookRepository
                .findAll(specification, pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto findById(Long id) {
        return bookRepository
                .findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book with id %d not found".formatted(id)
                ));
    }

    @Override
    public BookDto update(Long id, CreateUpdateBookRequestDto createUpdateBookRequestDto) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book with id %d not found".formatted(id)
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

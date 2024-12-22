package bookstore.services.bookservice;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.CreateUpdateBookRequestDto;
import bookstore.exceptions.EntityNotFoundException;
import bookstore.mappers.book.IBookMapper;
import bookstore.models.Book;
import bookstore.repositories.bookrepository.IBookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService implements IBookService {
    private final IBookRepository bookRepository;

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

        book.setTitle(createUpdateBookRequestDto.getTitle());
        book.setAuthor(createUpdateBookRequestDto.getAuthor());
        book.setIsbn(createUpdateBookRequestDto.getIsbn());
        book.setPrice(createUpdateBookRequestDto.getPrice());
        book.setDescription(createUpdateBookRequestDto.getDescription());
        book.setCoverImage(createUpdateBookRequestDto.getCoverImage());

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}

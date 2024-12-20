package bookstore.services.bookservice;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.CreateBookRequestDto;
import bookstore.exceptions.EntityNotFoundException;
import bookstore.mappers.book.IBookMapper;
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
    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        return bookMapper.toDto(
                bookRepository.save(
                        bookMapper.toEntity(createBookRequestDto)
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
}

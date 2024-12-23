package bookstore.controllers;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.BookSearchParamsDto;
import bookstore.dtos.book.CreateUpdateBookRequestDto;
import bookstore.services.bookservice.IBookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final IBookService bookService;

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public List<BookDto> searchBooks(BookSearchParamsDto bookSearchParamsDto) {
        return bookService.search(bookSearchParamsDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody CreateUpdateBookRequestDto createUpdateBookRequestDto) {
        return bookService.save(createUpdateBookRequestDto);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(
            @PathVariable Long id,
            @RequestBody CreateUpdateBookRequestDto createUpdateBookRequestDto
    ) {
        return bookService.update(id, createUpdateBookRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}

package bookstore.controllers;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.BookSearchParamsDto;
import bookstore.dtos.book.CreateUpdateBookRequestDto;
import bookstore.services.book.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management")
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final IBookService bookService;

    @Operation(description = "Get all books")
    @GetMapping
    public Page<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @Operation(description = "Get specific book")
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @Operation(description = "Search books by some parameters")
    @GetMapping("/search")
    public Page<BookDto> searchBooks(Pageable pageable, BookSearchParamsDto bookSearchParamsDto) {
        return bookService.search(pageable, bookSearchParamsDto);
    }

    @Operation(description = "Create book")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(
            @RequestBody
            @Valid
            CreateUpdateBookRequestDto createUpdateBookRequestDto
    ) {
        return bookService.save(createUpdateBookRequestDto);
    }

    @Operation(description = "Update specific book")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public BookDto updateBook(
            @PathVariable
            Long id,

            @RequestBody
            @Valid
            CreateUpdateBookRequestDto createUpdateBookRequestDto
    ) {
        return bookService.update(id, createUpdateBookRequestDto);
    }

    @Operation(description = "Delete specific book")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}

package bookstore.controllers;

import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.CreateBookRequestDto;

import java.util.List;

public interface IBookController {
    List<BookDto> getAll();

    BookDto getBookById(Long id);

    BookDto createBook(CreateBookRequestDto createBookRequestDto);
}

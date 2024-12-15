package bookstore.repositories.bookRepository;

import bookstore.models.Book;

import java.util.List;

public interface IBookRepository {
    Book save(Book book);

    List<Book> findAll();
}

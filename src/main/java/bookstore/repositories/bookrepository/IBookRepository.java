package bookstore.repositories.bookrepository;

import bookstore.models.Book;
import java.util.List;
import java.util.Optional;

public interface IBookRepository {
    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(Long id);
}

package bookstore.services.bookService;

import bookstore.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookService {
    Book save(Book book);

    List<Book> findAll();
}

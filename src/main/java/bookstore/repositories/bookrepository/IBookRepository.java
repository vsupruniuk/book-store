package bookstore.repositories.bookrepository;

import bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
}

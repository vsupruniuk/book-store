package bookstore.repositories.bookrepository;

import bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IBookRepository extends
        JpaRepository<Book, Long>,
        JpaSpecificationExecutor<Book> {
    Book findByIsbn(String isbn);
}

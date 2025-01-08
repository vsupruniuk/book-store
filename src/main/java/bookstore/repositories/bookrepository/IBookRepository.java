package bookstore.repositories.bookrepository;

import bookstore.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

public interface IBookRepository extends
        JpaRepository<Book, Long>,
        JpaSpecificationExecutor<Book> {
    boolean existsById(@NonNull Long id);

    boolean existsByIsbn(String isbn);

    @Query("FROM Book book LEFT JOIN FETCH book.categories")
    Page<Book> findAllWithCategories(Pageable pageable);

    @Query("FROM Book book JOIN book.categories category WHERE category.id = :categoryId")
    Page<Book> findAllByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
}

package bookstore.repositories;

import bookstore.dtos.book.BookSearchParamsDto;
import org.springframework.data.jpa.domain.Specification;

public interface ISpecificationBuilder<T> {
    Specification<T> build(BookSearchParamsDto bookSearchParamsDto);
}

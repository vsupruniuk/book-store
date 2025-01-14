package bookstore.repositories.book.specification.providers;

import bookstore.models.Book;
import bookstore.repositories.ISpecificationProvider;
import bookstore.repositories.book.specification.SpecificationKey;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements ISpecificationProvider<Book> {
    public SpecificationKey getKey() {
        return SpecificationKey.AUTHOR;
    }

    public Specification<Book> getSpecification(String param) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(
                        criteriaBuilder.lower(root.get(SpecificationKey.AUTHOR.getKey())),
                        "%" + param.toLowerCase() + "%"
                );
    }
}

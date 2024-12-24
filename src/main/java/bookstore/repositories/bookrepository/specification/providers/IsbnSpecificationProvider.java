package bookstore.repositories.bookrepository.specification.providers;

import bookstore.models.Book;
import bookstore.repositories.ISpecificationProvider;
import bookstore.repositories.bookrepository.specification.SpecificationKey;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsbnSpecificationProvider implements ISpecificationProvider<Book> {
    public SpecificationKey getKey() {
        return SpecificationKey.ISBN;
    }

    public Specification<Book> getSpecification(String param) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(
                        criteriaBuilder.lower(root.get(SpecificationKey.ISBN.getKey())),
                        "%" + param.toLowerCase() + "%"
                );
    }
}

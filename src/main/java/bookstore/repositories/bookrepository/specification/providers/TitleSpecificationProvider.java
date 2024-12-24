package bookstore.repositories.bookrepository.specification.providers;

import bookstore.models.Book;
import bookstore.repositories.ISpecificationProvider;
import bookstore.repositories.bookrepository.specification.SpecificationKey;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements ISpecificationProvider<Book> {
    public SpecificationKey getKey() {
        return SpecificationKey.TITLE;
    }

    public Specification<Book> getSpecification(String param) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(
                        criteriaBuilder.lower(root.get(SpecificationKey.TITLE.getKey())),
                        "%" + param.toLowerCase() + "%"
                );
    }
}

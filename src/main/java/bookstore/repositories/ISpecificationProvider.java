package bookstore.repositories;

import bookstore.repositories.bookrepository.specification.SpecificationKey;
import org.springframework.data.jpa.domain.Specification;

public interface ISpecificationProvider<T> {
    SpecificationKey getKey();

    Specification<T> getSpecification(String param);
}

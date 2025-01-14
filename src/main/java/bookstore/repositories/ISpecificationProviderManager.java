package bookstore.repositories;

import bookstore.repositories.book.specification.SpecificationKey;

public interface ISpecificationProviderManager<T> {
    ISpecificationProvider<T> getSpecificationProvider(SpecificationKey key);
}

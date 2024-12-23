package bookstore.repositories;

import bookstore.repositories.bookrepository.specification.SpecificationKey;

public interface ISpecificationProviderManager<T> {
    ISpecificationProvider<T> getSpecificationProvider(SpecificationKey key);
}

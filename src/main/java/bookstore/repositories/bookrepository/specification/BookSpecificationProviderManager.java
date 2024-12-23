package bookstore.repositories.bookrepository.specification;

import bookstore.exceptions.SpecificationNotFoundException;
import bookstore.models.Book;
import bookstore.repositories.ISpecificationProvider;
import bookstore.repositories.ISpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager
        implements ISpecificationProviderManager<Book> {
    private final List<ISpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public ISpecificationProvider<Book> getSpecificationProvider(SpecificationKey key) {
        return bookSpecificationProviders
                .stream()
                .filter(provider -> provider.getKey().equals(key))
                .findFirst()
                .orElseThrow(() ->
                        new SpecificationNotFoundException(
                                "No specification found for key %s".formatted(key)
                        )
                );
    }
}

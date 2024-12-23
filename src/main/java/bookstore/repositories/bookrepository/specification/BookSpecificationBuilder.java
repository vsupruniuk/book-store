package bookstore.repositories.bookrepository.specification;

import bookstore.dtos.book.BookSearchParamsDto;
import bookstore.models.Book;
import bookstore.repositories.ISpecificationBuilder;
import bookstore.repositories.ISpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements ISpecificationBuilder<Book> {
    private final ISpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParamsDto bookSearchParamsDto) {
        Specification<Book> specification = Specification.where(null);

        if (bookSearchParamsDto.getTitle() != null) {
            specification = specification.and(
                    bookSpecificationProviderManager
                            .getSpecificationProvider(SpecificationKey.TITLE)
                            .getSpecification(bookSearchParamsDto.getTitle())
            );
        }

        if (bookSearchParamsDto.getAuthor() != null) {
            specification = specification.and(
                    bookSpecificationProviderManager
                            .getSpecificationProvider(SpecificationKey.AUTHOR)
                            .getSpecification(bookSearchParamsDto.getAuthor())
            );
        }

        if (bookSearchParamsDto.getIsbn() != null) {
            specification = specification.and(
                    bookSpecificationProviderManager
                            .getSpecificationProvider(SpecificationKey.ISBN)
                            .getSpecification(bookSearchParamsDto.getIsbn())
            );
        }

        return specification;
    }
}

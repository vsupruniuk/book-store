package bookstore.repositories.book.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpecificationKey {
    TITLE("title"),
    AUTHOR("author"),
    ISBN("isbn");

    private final String key;
}

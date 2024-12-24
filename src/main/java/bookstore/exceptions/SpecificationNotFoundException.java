package bookstore.exceptions;

public class SpecificationNotFoundException extends RuntimeException {
    public SpecificationNotFoundException(String message) {
        super(message);
    }
}

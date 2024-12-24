package bookstore.exceptions;

public class IsbnConflictException extends RuntimeException {
    public IsbnConflictException(String message) {
        super(message);
    }
}

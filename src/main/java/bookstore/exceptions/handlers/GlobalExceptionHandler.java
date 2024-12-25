package bookstore.exceptions.handlers;

import bookstore.exceptions.EntityNotFoundException;
import bookstore.exceptions.IsbnConflictException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String INTERNAL_SERVER_ERROR_MSG = "Unexpected error occurred";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        return getSimpleResponse(
                HttpStatus.BAD_REQUEST,
                ex
                    .getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundEntity(EntityNotFoundException ex) {
        return getSimpleResponse(
                HttpStatus.NOT_FOUND,
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(IsbnConflictException.class)
    public ResponseEntity<Map<String, Object>> handleIsbnConflictException(
            IsbnConflictException ex
    ) {
        return getSimpleResponse(
                HttpStatus.CONFLICT,
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllException() {
        return getSimpleResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                List.of(INTERNAL_SERVER_ERROR_MSG)
        );
    }

    private Map<String, Object> getResponseBody(HttpStatus status, List<String> errors) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", status);
        body.put("errors", errors);

        return body;
    }

    private ResponseEntity<Map<String, Object>> getSimpleResponse(
            HttpStatus status,
            List<String> errors
    ) {
        return new ResponseEntity<>(
                getResponseBody(
                        status,
                        errors
                ),
                status
        );
    }
}

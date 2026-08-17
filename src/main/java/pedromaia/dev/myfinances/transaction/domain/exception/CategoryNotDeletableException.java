package pedromaia.dev.myfinances.transaction.domain.exception;

public class CategoryNotDeletableException extends RuntimeException {
    public CategoryNotDeletableException(String message) {
        super(message);
    }
}

package pedromaia.dev.myfinances.account.domain.exception;

public class CategoryNotDeletableException extends RuntimeException {
    public CategoryNotDeletableException(String message) {
        super(message);
    }
}

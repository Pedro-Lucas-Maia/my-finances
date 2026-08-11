package pedromaia.dev.myfinances.transaction.domain;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message) {
        super(message);
    }
}

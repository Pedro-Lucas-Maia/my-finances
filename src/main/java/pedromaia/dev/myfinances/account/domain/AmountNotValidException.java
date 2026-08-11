package pedromaia.dev.myfinances.account.domain;

public class AmountNotValidException extends RuntimeException {
    public AmountNotValidException(String message) {
        super(message);
    }
}

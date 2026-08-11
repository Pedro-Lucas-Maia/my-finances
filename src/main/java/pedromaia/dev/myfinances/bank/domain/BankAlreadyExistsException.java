package pedromaia.dev.myfinances.bank.domain;

public class BankAlreadyExistsException extends RuntimeException {
    public BankAlreadyExistsException(String message) {
        super(message);
    }
}

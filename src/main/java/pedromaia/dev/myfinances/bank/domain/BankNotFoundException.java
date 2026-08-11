package pedromaia.dev.myfinances.bank.domain;

public class BankNotFoundException extends RuntimeException {
    public BankNotFoundException(String message) {
        super(message);
    }
}

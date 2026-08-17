package pedromaia.dev.myfinances.transaction.domain.exception;

import pedromaia.dev.myfinances.common.domain.exceptions.NotFoundException;

public class TransactionNotFoundException extends NotFoundException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}

package pedromaia.dev.myfinances.bank.domain;

import pedromaia.dev.myfinances.common.domain.exceptions.AlreadyExistsException;

public class BankAlreadyExistsException extends AlreadyExistsException {
    public BankAlreadyExistsException(String message) {
        super(message);
    }
}

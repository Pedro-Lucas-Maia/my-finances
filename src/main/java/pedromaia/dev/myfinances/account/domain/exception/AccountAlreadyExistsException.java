package pedromaia.dev.myfinances.account.domain.exception;

import pedromaia.dev.myfinances.common.domain.exceptions.AlreadyExistsException;

public class AccountAlreadyExistsException extends AlreadyExistsException {
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}

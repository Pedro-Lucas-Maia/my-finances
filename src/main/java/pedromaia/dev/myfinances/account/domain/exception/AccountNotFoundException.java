package pedromaia.dev.myfinances.account.domain.exception;

import pedromaia.dev.myfinances.common.domain.exceptions.NotFoundException;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

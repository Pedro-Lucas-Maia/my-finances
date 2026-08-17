package pedromaia.dev.myfinances.account.domain.exception;

import pedromaia.dev.myfinances.common.domain.exceptions.DomainException;

public class AmountNotValidException extends DomainException {
    public AmountNotValidException(String message) {
        super(message);
    }
}

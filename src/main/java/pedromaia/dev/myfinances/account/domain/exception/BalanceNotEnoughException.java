package pedromaia.dev.myfinances.account.domain.exception;

import pedromaia.dev.myfinances.common.domain.exceptions.DomainException;

public class BalanceNotEnoughException extends DomainException {
    public BalanceNotEnoughException(String message) {
        super(message);
    }
}

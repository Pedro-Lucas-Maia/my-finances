package pedromaia.dev.myfinances.account.domain.exception;

import pedromaia.dev.myfinances.common.domain.exceptions.DomainException;

public class CategoryNameNotValidException extends DomainException {
    public CategoryNameNotValidException(String message) {
        super(message);
    }
}

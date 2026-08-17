package pedromaia.dev.myfinances.account.domain.exception;

import pedromaia.dev.myfinances.common.domain.exceptions.AlreadyExistsException;

public class CategoryAlreadyExistsException extends AlreadyExistsException {
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}

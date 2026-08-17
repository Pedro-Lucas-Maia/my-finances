package pedromaia.dev.myfinances.transaction.domain.exception;

import pedromaia.dev.myfinances.common.domain.exceptions.AlreadyExistsException;

public class CategoryTransactionAlreadyExistsException extends AlreadyExistsException {
    public CategoryTransactionAlreadyExistsException(String message) {
        super(message);
    }
}

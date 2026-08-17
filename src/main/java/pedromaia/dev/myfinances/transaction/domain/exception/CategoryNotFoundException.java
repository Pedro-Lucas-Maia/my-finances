package pedromaia.dev.myfinances.transaction.domain.exception;

import pedromaia.dev.myfinances.common.domain.exceptions.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}

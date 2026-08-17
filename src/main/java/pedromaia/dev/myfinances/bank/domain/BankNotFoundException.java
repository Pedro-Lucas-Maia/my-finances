package pedromaia.dev.myfinances.bank.domain;

import pedromaia.dev.myfinances.common.domain.exceptions.NotFoundException;

public class BankNotFoundException extends NotFoundException {
    public BankNotFoundException(String message) {
        super(message);
    }
}

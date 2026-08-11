package pedromaia.dev.myfinances.bank.infrastructure.http.request;

import pedromaia.dev.myfinances.bank.application.input.CreateBankInput;

public record CreateBankRequest(String name) {
    public CreateBankInput toInput() {
        return new CreateBankInput(name);
    }
}

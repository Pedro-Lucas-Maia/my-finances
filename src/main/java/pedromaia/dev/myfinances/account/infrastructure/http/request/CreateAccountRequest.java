package pedromaia.dev.myfinances.account.infrastructure.http.request;

import pedromaia.dev.myfinances.account.application.management.CreateAccountInput;

import java.math.BigDecimal;

public record CreateAccountRequest(String name, BigDecimal balance, String categoryId, String bankId) {
    public static CreateAccountInput toInput (CreateAccountRequest request) {
        return new CreateAccountInput(
                request.name(),
                request.balance,
                request.categoryId,
                request.bankId
        );
    }
}

package pedromaia.dev.myfinances.account.infrastructure.http.request;

import pedromaia.dev.myfinances.account.application.operations.RegisterIncomeInput;

import java.math.BigDecimal;

public record RegisterIncomeRequest(
        String description,
        BigDecimal amount,
        String categoryId
) {
    public static RegisterIncomeInput toInput(String accountId, RegisterIncomeRequest request) {
        return new RegisterIncomeInput(
                request.description,
                request.amount,
                request.categoryId,
                accountId
        );
    }
}

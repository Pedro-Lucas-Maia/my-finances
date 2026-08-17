package pedromaia.dev.myfinances.account.infrastructure.http.request;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.account.application.operations.RegisterExpenseInput;

import java.math.BigDecimal;

public record RegisterExpenseRequest(
        String description,
        BigDecimal amount,
        String CategoryId
) {
    public static RegisterExpenseInput toInput(String accountId, @NonNull RegisterExpenseRequest request) {
        return new RegisterExpenseInput(
                request.description,
                request.amount,
                request.CategoryId,
                accountId
        );
    }
}

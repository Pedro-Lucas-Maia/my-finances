package pedromaia.dev.myfinances.account.infrastructure.http.response;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.account.application.operations.RegisterIncomeOutput;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record RegisterIncomeResponse(
        String transactionId,
        String description,
        BigDecimal amount,
        Category category,
        String type,
        Timestamp timestamp,
        AccountReceiver accountReceiver
) {
    public record AccountReceiver(String accountId, String name, BigDecimal balance) {}
    public record Category(String categoryId, String name) {}

    public static RegisterIncomeResponse from(@NonNull RegisterIncomeOutput output) {
        return new RegisterIncomeResponse(
                output.transactionId(),
                output.description(),
                output.amount(),
                new Category(output.category().categoryId(), output.category().name()),
                output.type(),
                output.timestamp(),
                new AccountReceiver(output.accountReceiver().accountId(), output.accountReceiver().name(), output.accountReceiver().balance())
        );
    }
}

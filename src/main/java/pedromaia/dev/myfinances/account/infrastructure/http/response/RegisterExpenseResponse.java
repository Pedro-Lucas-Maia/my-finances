package pedromaia.dev.myfinances.account.infrastructure.http.response;

import pedromaia.dev.myfinances.account.application.operations.RegisterExpenseOutput;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record RegisterExpenseResponse(
        String transactionId,
        String description,
        BigDecimal amount,
        Category category,
        String type,
        Timestamp timestamp,
        AccountSender accountSender
) {
    public record Category(String categoryId, String name) {}
    public record AccountSender(String accountId, String name, BigDecimal balance) {}

    public static RegisterExpenseResponse from(RegisterExpenseOutput output) {
        return new RegisterExpenseResponse(
                output.transactionId(),
                output.description(),
                output.amount(),
                new Category(output.category().categoryId(), output.category().name()),
                output.type(),
                output.timestamp(),
                new AccountSender(output.accountSender().accountId(), output.accountSender().name(), output.accountSender().balance())
        );
    }
}

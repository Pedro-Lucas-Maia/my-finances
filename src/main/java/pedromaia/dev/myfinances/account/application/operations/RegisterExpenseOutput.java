package pedromaia.dev.myfinances.account.application.operations;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.account.application.ports.TransactionReceipt;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record RegisterExpenseOutput(
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

    public static RegisterExpenseOutput from(@NonNull TransactionReceipt transaction, String accountName, BigDecimal balance) {
        return new RegisterExpenseOutput(
                transaction.transactionId(),
                transaction.description(),
                transaction.amount(),
                new Category(transaction.category().categoryId(), transaction.category().name()),
                transaction.type(),
                transaction.timestamp(),
                new AccountSender(transaction.accountSenderId(), accountName, balance)
                );
    }
}

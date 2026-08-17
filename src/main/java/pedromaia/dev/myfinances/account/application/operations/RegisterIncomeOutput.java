package pedromaia.dev.myfinances.account.application.operations;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.account.application.ports.TransactionReceipt;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record RegisterIncomeOutput(
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
    public static RegisterIncomeOutput from(@NonNull TransactionReceipt transaction, String accountName, BigDecimal balance) {
        return new RegisterIncomeOutput(
                transaction.transactionId(),
                transaction.description(),
                transaction.amount(),
                new Category(transaction.category().categoryId(), transaction.category().name()),
                transaction.type(),
                transaction.timestamp(),
                new AccountReceiver(transaction.accountSenderId(), accountName, balance)
        );
    }
}

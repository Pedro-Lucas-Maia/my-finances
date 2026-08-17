package pedromaia.dev.myfinances.transaction.application;


import pedromaia.dev.myfinances.transaction.domain.Transaction;

import java.math.BigDecimal;
import java.util.Optional;

public record TransactionOutput(
        String id,
        BigDecimal amount,
        String description,
        Category category,
        String type,
        Optional<AccountReceiver> accountReceiver,
        Optional<AccountSender> accountSender
) {
    public record Category(String categoryId, String name){}
    public record AccountReceiver(String accountReceiverId, String name) {}
    public record AccountSender(String accountSenderId, String name) {}

    public static TransactionOutput from(Transaction transaction, String categoryName, AccountReceiver accountReceiver, AccountSender accountSender) {
        return new TransactionOutput(
                transaction.getId().uuid().toString(),
                transaction.getAmount(),
                transaction.getDescription(),
                new Category(transaction.getCategoryId().uuid().toString(), categoryName),
                transaction.getType().toString(),
                Optional.ofNullable(accountReceiver),
                Optional.ofNullable(accountSender)
        );
    }
}

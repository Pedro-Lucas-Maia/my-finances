package pedromaia.dev.myfinances.account.application.operations;

import pedromaia.dev.myfinances.account.application.ports.TransactionReceipt;
import pedromaia.dev.myfinances.account.domain.Account;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record RegisterTransferOutput(
        String transactionId,
        String description,
        BigDecimal amount,
        Category category,
        String type,
        Timestamp timestamp,
        AccountReceiver accountReceiver,
        AccountSender accountSender
) {
    public record Category(String categoryId, String name) {}
    public record AccountReceiver(String accountReceiverId, String name, BigDecimal balance) {}
    public record AccountSender(String accountSenderId, String name, BigDecimal balance) {}

    public static RegisterTransferOutput from(TransactionReceipt transaction, Account accountReceiver, Account accountSender) {
        return new RegisterTransferOutput(
                transaction.transactionId(),
                transaction.description(),
                transaction.amount(),
                new Category(transaction.category().categoryId(), transaction.category().name()),
                transaction.type(),
                transaction.timestamp(),
                new AccountReceiver(transaction.accountSenderId(), accountReceiver.getName(), accountReceiver.getBalance()),
                new AccountSender(transaction.accountSenderId(), accountSender.getName(), accountSender.getBalance())
        );
    }
}

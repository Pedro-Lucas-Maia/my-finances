package pedromaia.dev.myfinances.transaction.infrastructure.http.response;

import pedromaia.dev.myfinances.transaction.application.output.TransactionOutput;

import java.math.BigDecimal;

public record TransactionResponse(
        String transactionId,
        BigDecimal amount,
        String description,
        Category category,
        String type,
        AccountReceiver accountReceiver,
        AccountSender accountSender
) {
    public static TransactionResponse from(TransactionOutput output) {
        return new TransactionResponse(
                output.id(),
                output.amount(),
                output.description(),
                new Category(output.category().categoryId(), output.category().name()),
                output.type(),
                output.accountReceiver()
                        .map(acc -> new AccountReceiver(acc.accountReceiverId(), acc.name()))
                        .orElse(null),
                output.accountSender()
                        .map(acc -> new AccountSender(acc.accountSenderId(), acc.name()))
                        .orElse(null)
        );
    }

    public record Category(String CategoryId, String name) {}
    public record AccountReceiver(String accountReceiverId, String name) {}
    public record AccountSender(String accountSenderId, String name) {}
}

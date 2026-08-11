package pedromaia.dev.myfinances.account.infrastructure.http.response;

import pedromaia.dev.myfinances.account.application.operations.RegisterTransferOutput;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record RegisterTransferResponse(
        String transactionId,
        String description,
        BigDecimal amount,
        Category category,
        String type,
        Timestamp timestamp,
        AccountReceiver accountReceiver,
        AccountSender accountSender
) {
    public record Category(String categoryId, String name) {
    }

    public record AccountReceiver(String accountReceiverId, String name, BigDecimal balance) {
    }

    public record AccountSender(String accountSenderId, String name, BigDecimal balance) {
    }

    public static RegisterTransferResponse from(RegisterTransferOutput output) {
        return new RegisterTransferResponse(
                output.transactionId(),
                output.description(),
                output.amount(),
                new Category(output.transactionId(), output.description()),
                output.type(),
                output.timestamp(),
                new AccountReceiver(output.accountReceiver().accountReceiverId(), output.accountReceiver().name(), output.accountReceiver().balance()),
                new AccountSender(output.accountSender().accountSenderId(), output.accountSender().name(), output.accountSender().balance())
        );
    }
}

package pedromaia.dev.myfinances.account.application.ports;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record TransactionReceipt(
        String transactionId,
        String description,
        BigDecimal amount,
        Category category,
        String type,
        Timestamp timestamp,
        String accountReceiverId,
        String accountSenderId
) {
    public record Category(String categoryId, String name) {}
}

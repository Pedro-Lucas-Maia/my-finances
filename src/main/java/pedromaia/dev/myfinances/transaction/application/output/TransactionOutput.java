package pedromaia.dev.myfinances.transaction.application.output;


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
}

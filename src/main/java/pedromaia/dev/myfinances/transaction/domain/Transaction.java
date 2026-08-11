package pedromaia.dev.myfinances.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pedromaia.dev.myfinances.account.domain.AccountId;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Transaction {
    private TransactionId id;
    private String description;
    private BigDecimal amount;
    private CategoryId categoryId;
    private Type type;
    private Timestamp date;
    private AccountId accountReceiverId;
    private AccountId accountSenderId;

    public Transaction(String description, BigDecimal amount, CategoryId categoryId, Type type, AccountId accountReceiverId, AccountId accountSenderId) {
        this.id = new TransactionId();
        this.description = description;
        this.amount = amount;
        this.categoryId = categoryId;
        this.type = type;
        this.date = new Timestamp(System.currentTimeMillis());
        this.accountReceiverId = accountReceiverId;
        this.accountSenderId = accountSenderId;
    }
    public static Transaction createIncome(String description, BigDecimal amount, String categoryId, AccountId accountReceiverId) {
        return new Transaction(
                new TransactionId(),
                description,
                amount,
                new CategoryId(UUID.fromString(categoryId)),
                Type.INCOME,
                new Timestamp(System.currentTimeMillis()),
                accountReceiverId,
                null
        );
    }

    public static Transaction createExpense(String description, BigDecimal amount, String categoryId, AccountId accountSenderId) {
        return new Transaction(
          new TransactionId(),
          description,
          amount,
          new CategoryId(UUID.fromString(categoryId)),
          Type.EXPENSE,
          new Timestamp(System.currentTimeMillis()),
          null,
          accountSenderId
        );
    }

    public static Transaction createTransfer(String description, BigDecimal amount, String categoryId, AccountId accountReceiverId, AccountId accountSenderId) {
        return new Transaction(
                new TransactionId(),
                description,
                amount,
                new CategoryId(UUID.fromString(categoryId)),
                Type.TRANSFER,
                new Timestamp(System.currentTimeMillis()),
                accountReceiverId,
                accountSenderId
        );
    }
}

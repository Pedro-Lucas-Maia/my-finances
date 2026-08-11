package pedromaia.dev.myfinances.transaction.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pedromaia.dev.myfinances.account.domain.AccountId;
import pedromaia.dev.myfinances.transaction.domain.CategoryId;
import pedromaia.dev.myfinances.transaction.domain.Transaction;
import pedromaia.dev.myfinances.transaction.domain.TransactionId;
import pedromaia.dev.myfinances.transaction.domain.Type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    @Id
    private UUID id;
    private String description;
    private BigDecimal amount;
    private String categoryId;

    @Enumerated(EnumType.STRING)
    private Type type;

    private Timestamp date;

    private String accountReceiverId;
    private String accountSenderId;


    public static TransactionEntity from(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId().uuid(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getCategoryId().uuid().toString(),
                transaction.getType(),
                transaction.getDate(),
                transaction.getAccountReceiverId().uuid().toString(),
                transaction.getAccountSenderId().uuid().toString()
        );
    }

    public Transaction toDomain() {
        return new Transaction(
                new TransactionId(this.id),
                this.description,
                this.amount,
                new CategoryId(UUID.fromString(this.categoryId)),
                this.type,
                this.date,
                new AccountId(UUID.fromString(this.accountReceiverId)),
                new AccountId(UUID.fromString(this.accountSenderId))
        );
    }
}

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
    private UUID categoryId;

    @Enumerated(EnumType.STRING)
    private Type type;

    private Timestamp date;

    @Column(name = "receivers_account_id")
    private UUID accountReceiverId;
    @Column(name = "payers_account_id")
    private UUID accountSenderId;
    public static TransactionEntity from(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId().uuid(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getCategoryId().uuid(),
                transaction.getType(),
                transaction.getDate(),
                transaction.getAccountReceiverId() != null ? transaction.getAccountReceiverId().uuid() : null,
                transaction.getAccountSenderId() != null ? transaction.getAccountSenderId().uuid() : null
        );
    }

    public Transaction toDomain() {
        return new Transaction(
                new TransactionId(this.id),
                this.description,
                this.amount,
                new CategoryId(this.categoryId),
                this.type,
                this.date,
                this.accountReceiverId != null ? new AccountId(this.accountReceiverId) : null,
                this.accountSenderId != null ? new AccountId(this.accountSenderId) : null
        );
    }
}

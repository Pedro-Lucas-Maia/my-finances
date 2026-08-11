package pedromaia.dev.myfinances.account.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pedromaia.dev.myfinances.account.domain.Account;
import pedromaia.dev.myfinances.account.domain.AccountId;
import pedromaia.dev.myfinances.account.domain.CategoryId;
import pedromaia.dev.myfinances.bank.domain.BankId;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    @Id
    private UUID id;

    private String name;
    private BigDecimal balance;

    //TODO: change categoryId at the migration file to add account categoryId table and foreign key
    @Column(name = "category_id")
    private UUID categoryId;

    @Column(name = "bank_id")
    private UUID bankId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public static AccountEntity from(Account account) {
        return new AccountEntity(
                account.getId().uuid(),
                account.getName(),
                account.getBalance(),
                account.getCategoryId().uuid(),
                account.getBankId().uuid(),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
    }

    public static Account toDomain(AccountEntity accountEntity) {
        return new Account(
                new AccountId(accountEntity.getId()),
                accountEntity.getName(),
                accountEntity.getBalance(),
                new CategoryId(accountEntity.getCategoryId()),
                new BankId(accountEntity.getBankId())
        );
    }
}

package pedromaia.dev.myfinances.bank.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pedromaia.dev.myfinances.bank.domain.Bank;
import pedromaia.dev.myfinances.bank.domain.BankId;

import java.util.UUID;

@Entity
@Table(name = "bank")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankEntity {
    @Id
    private UUID id;

    String name;

    public static BankEntity from(Bank bank) {
        return new BankEntity(bank.getId().uuid(), bank.getName());
    }

    public static Bank toDomain(BankEntity bankEntity) {
        return new Bank(new BankId(bankEntity.getId()), bankEntity.getName());
    }
}

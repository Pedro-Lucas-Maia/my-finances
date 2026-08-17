package pedromaia.dev.myfinances.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.account.domain.exception.AmountNotValidException;
import pedromaia.dev.myfinances.account.domain.exception.BalanceNotEnoughException;
import pedromaia.dev.myfinances.bank.domain.BankId;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Account {
    private AccountId id;
    private String name;
    private BigDecimal balance;
    private CategoryId categoryId;
    private BankId bankId;

    public Account(String name, BigDecimal balance, CategoryId category, BankId bankId) {
        this.id = new AccountId();
        this.name = name;
        this.balance = balance;
        this.categoryId = category;
        this.bankId = bankId;
    }

    public Account registerIncome(BigDecimal amount) {
        checkAmount(amount);
        this.balance = this.balance.add(amount);
        return this;
    }

    public Account registerExpense(BigDecimal amount) {
        checkAmount(amount);
        checkBalance(amount);
        this.balance = this.balance.subtract(amount);
        return this;
    }

    private void checkAmount(@NonNull BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AmountNotValidException("amount must be positive");
        }
    }
    private void checkBalance(@NonNull BigDecimal amount) {
        if (this.balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new BalanceNotEnoughException("Balance is not enough for this operation");
        }
    }
}

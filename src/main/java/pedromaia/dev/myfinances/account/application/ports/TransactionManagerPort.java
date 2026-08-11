package pedromaia.dev.myfinances.account.application.ports;

import pedromaia.dev.myfinances.account.domain.AccountId;

import java.math.BigDecimal;

public interface TransactionManagerPort {
    TransactionReceipt registerIncome(String description, BigDecimal amount, String categoryId, AccountId accountId);
    TransactionReceipt registerExpense(String description, BigDecimal amount, String categoryId, AccountId accountId);
    TransactionReceipt registerTransfer(String description, BigDecimal amount, String categoryId, AccountId receiver, AccountId sender);
}

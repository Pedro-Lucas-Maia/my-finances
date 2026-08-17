package pedromaia.dev.myfinances.transaction.infrastructure.adapters;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import pedromaia.dev.myfinances.account.application.ports.TransactionManagerPort;
import pedromaia.dev.myfinances.account.application.ports.TransactionReceipt;
import pedromaia.dev.myfinances.account.domain.AccountId;
import pedromaia.dev.myfinances.transaction.domain.CategoryRepository;
import pedromaia.dev.myfinances.transaction.domain.Transaction;
import pedromaia.dev.myfinances.transaction.domain.TransactionRepository;
import pedromaia.dev.myfinances.transaction.domain.exception.CategoryNotFoundException;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class AccountTransactionAdapter implements TransactionManagerPort {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public AccountTransactionAdapter(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public TransactionReceipt registerIncome(String description, BigDecimal amount, String categoryId, AccountId accountId) {
        var category = categoryRepository.findById(UUID.fromString(categoryId))
                .orElseThrow(() -> new CategoryNotFoundException("Category with the id " + categoryId + " not found"));

        var transaction = Transaction.createIncome(description, amount, categoryId, accountId);
        var savedTransaction = transactionRepository.save(transaction);

        return new TransactionReceipt(
                savedTransaction.getId().uuid().toString(),
                savedTransaction.getDescription(),
                savedTransaction.getAmount(),
                new TransactionReceipt.Category(savedTransaction.getCategoryId().uuid().toString(), category.getName()),
                savedTransaction.getType().toString(),
                savedTransaction.getDate(),
                savedTransaction.getAccountReceiverId().uuid().toString(),
                null
        );
    }

    @Transactional
    @Override
    public TransactionReceipt registerExpense(String description, BigDecimal amount, String categoryId, AccountId accountId) {
        var category = categoryRepository.findById(UUID.fromString(categoryId))
                .orElseThrow(() -> new CategoryNotFoundException("Category with the id " + categoryId + " not found"));

        var transaction = Transaction.createExpense(description, amount, categoryId, accountId);
        var savedTransaction = transactionRepository.save(transaction);

        return new TransactionReceipt(
                savedTransaction.getId().uuid().toString(),
                savedTransaction.getDescription(),
                savedTransaction.getAmount(),
                new TransactionReceipt.Category(savedTransaction.getCategoryId().uuid().toString(), category.getName()),
                savedTransaction.getType().toString(),
                savedTransaction.getDate(),
                null,
                savedTransaction.getAccountSenderId().uuid().toString()
        );
    }

    @Transactional
    @Override
    public TransactionReceipt registerTransfer(String description, BigDecimal amount, String categoryId, AccountId receiver, AccountId sender) {
        var category = categoryRepository.findById(UUID.fromString(categoryId))
                .orElseThrow(() -> new CategoryNotFoundException("Category with the id " + categoryId + " not found"));

        var transaction = Transaction.createTransfer(description, amount, categoryId, receiver, sender);
        var savedTransaction = transactionRepository.save(transaction);

        return new TransactionReceipt(
                savedTransaction.getId().uuid().toString(),
                savedTransaction.getDescription(),
                savedTransaction.getAmount(),
                new TransactionReceipt.Category(savedTransaction.getCategoryId().uuid().toString(), category.getName()),
                savedTransaction.getType().toString(),
                savedTransaction.getDate(),
                transaction.getAccountReceiverId().uuid().toString(),
                savedTransaction.getAccountSenderId().uuid().toString()
        );
    }
}

package pedromaia.dev.myfinances.transaction.application.output;

import org.springframework.stereotype.Component;
import pedromaia.dev.myfinances.account.domain.AccountRepository;
import pedromaia.dev.myfinances.transaction.domain.CategoryNotFoundException;
import pedromaia.dev.myfinances.transaction.domain.CategoryRepository;
import pedromaia.dev.myfinances.transaction.domain.Transaction;

import java.util.Optional;

@Component
public class TransactionOutputAssembler {
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;

    public TransactionOutputAssembler(CategoryRepository categoryRepository, AccountRepository accountRepository) {
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionOutput from(Transaction transaction) {
        var category = categoryRepository.findById(transaction.getCategoryId().uuid())
                .orElseThrow(() -> new CategoryNotFoundException("Category with the id " + transaction.getCategoryId().uuid() + " not found"));

        Optional<TransactionOutput.AccountReceiver> receiver = Optional.ofNullable(transaction.getAccountReceiverId())
                .flatMap(accountId -> accountRepository.findById(accountId.uuid()))
                .map(account -> new TransactionOutput.AccountReceiver(account.getId().uuid().toString(), account.getName()));

        Optional<TransactionOutput.AccountSender> sender = Optional.ofNullable(transaction.getAccountSenderId())
                .flatMap(accountId -> accountRepository.findById(accountId.uuid()))
                .map(account -> new TransactionOutput.AccountSender(account.getId().uuid().toString(), account.getName()));

        return new TransactionOutput(
                transaction.getId().uuid().toString(),
                transaction.getAmount(),
                transaction.getDescription(),
                new TransactionOutput.Category(category.getCategoryId().uuid().toString(), category.getName()),
                transaction.getType().toString(),
                receiver,
                sender
        );
    }


}

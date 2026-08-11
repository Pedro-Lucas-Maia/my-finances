package pedromaia.dev.myfinances.account.application.operations;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.application.ports.TransactionManagerPort;
import pedromaia.dev.myfinances.account.domain.AccountNotFoundException;
import pedromaia.dev.myfinances.account.domain.AccountRepository;

import java.util.UUID;

@Service
public class RegisterExpenseUseCase {
    private final AccountRepository accountRepository;
    private final TransactionManagerPort transactionManagerPort;

    public RegisterExpenseUseCase(AccountRepository accountRepository, TransactionManagerPort transactionManagerPort) {
        this.accountRepository = accountRepository;
        this.transactionManagerPort = transactionManagerPort;
    }

    @Transactional
    public RegisterExpenseOutput execute(@NonNull RegisterExpenseInput input) {
        var accountSender = accountRepository.findById(UUID.fromString(input.accountSenderId()))
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + input.accountSenderId() + " not found"));


        var updatedAccount = accountSender.registerExpense(input.amount());
        var receipt = transactionManagerPort.registerExpense(input.description(), input.amount(), input.categoryId(), updatedAccount.getId());

        accountRepository.save(updatedAccount);

        return RegisterExpenseOutput.from(receipt, updatedAccount.getName(), updatedAccount.getBalance());
    }
}

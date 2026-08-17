package pedromaia.dev.myfinances.account.application.operations;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.application.ports.TransactionManagerPort;
import pedromaia.dev.myfinances.account.domain.AccountRepository;
import pedromaia.dev.myfinances.account.domain.exception.AccountNotFoundException;

import java.util.UUID;

@Service
public class RegisterIncomeUseCase {
    private final AccountRepository accountRepository;
    private final TransactionManagerPort transactionManagerPort;

    public RegisterIncomeUseCase(AccountRepository accountRepository, TransactionManagerPort transactionManagerPort) {
        this.accountRepository = accountRepository;
        this.transactionManagerPort = transactionManagerPort;
    }

    @Transactional
    @Tool(description = "Registra uma receita/ganho", name = "registerIncomeTool")
    public RegisterIncomeOutput execute(@NonNull RegisterIncomeInput input) {
        var accountReceiver = accountRepository.findById(UUID.fromString(input.accountReceiverId()))
                .orElseThrow(() -> new AccountNotFoundException("Account with the id " + input.accountReceiverId() + " not found"));

        var updatedAccount = accountReceiver.registerIncome(input.amount());
        var receipt = transactionManagerPort.registerIncome(input.description(), input.amount(), input.categoryId(), updatedAccount.getId());

        accountRepository.save(updatedAccount);

        return RegisterIncomeOutput.from(receipt, updatedAccount.getName(), updatedAccount.getBalance());
    }
}

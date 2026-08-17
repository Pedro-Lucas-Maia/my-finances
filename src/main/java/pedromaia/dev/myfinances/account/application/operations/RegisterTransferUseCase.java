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
public class RegisterTransferUseCase {
    private final AccountRepository accountRepository;
    private final TransactionManagerPort transactionManagerPort;

    public RegisterTransferUseCase(AccountRepository accountRepository, TransactionManagerPort transactionManagerPort) {
        this.accountRepository = accountRepository;
        this.transactionManagerPort = transactionManagerPort;
    }

    @Transactional
    @Tool(description = "registra uma transferência entre contas cadastradas", name = "registerTransferTool")
    public RegisterTransferOutput execute(@NonNull RegisterTransferInput input) {
        var accountReceiver = accountRepository.findById(UUID.fromString(input.accountReceiverId()))
                .orElseThrow(() -> new AccountNotFoundException("Account with the id " + input.accountReceiverId() + " not found"));

        var accountSender = accountRepository.findById(UUID.fromString(input.accountSenderId()))
                .orElseThrow(() -> new AccountNotFoundException("Account with the id " + input.accountSenderId() + " not found"));

        var updatedSender = accountSender.registerExpense(input.amount());
        var updatedReceiver = accountReceiver.registerIncome(input.amount());

        var receipt = transactionManagerPort.registerTransfer(input.description(), input.amount(), input.categoryId(), updatedReceiver.getId(), updatedSender.getId());

        return RegisterTransferOutput.from(receipt, accountRepository.save(updatedReceiver), accountRepository.save(updatedSender));
    }
}

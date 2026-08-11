package pedromaia.dev.myfinances.account.application.management;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.domain.AccountNotFoundException;
import pedromaia.dev.myfinances.account.domain.AccountRepository;

import java.util.UUID;

@Service
public class DeleteAccountUseCase {
    private final AccountRepository accountRepository;

    public DeleteAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void execute(DeleteAccountInput input) {
        var account = accountRepository.findById(UUID.fromString(input.accountId()))
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + input.accountId() + " not found"));
        accountRepository.delete(account);
    }
}

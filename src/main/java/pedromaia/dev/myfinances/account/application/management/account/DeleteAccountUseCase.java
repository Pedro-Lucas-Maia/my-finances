package pedromaia.dev.myfinances.account.application.management.account;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.domain.AccountRepository;
import pedromaia.dev.myfinances.account.domain.exception.AccountNotFoundException;

import java.util.UUID;

@Service
public class DeleteAccountUseCase {
    private final AccountRepository accountRepository;

    public DeleteAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @CacheEvict(value = "accounts-context", allEntries = true)
    public void execute(@NonNull DeleteAccountInput input) {
        var account = accountRepository.findById(UUID.fromString(input.accountId()))
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + input.accountId() + " not found"));
        accountRepository.delete(account);
    }
}

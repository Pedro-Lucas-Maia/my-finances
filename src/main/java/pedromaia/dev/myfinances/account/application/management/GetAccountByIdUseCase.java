package pedromaia.dev.myfinances.account.application.management;

import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.domain.AccountNotFoundException;
import pedromaia.dev.myfinances.account.domain.AccountRepository;

import java.util.UUID;

@Service
public class GetAccountByIdUseCase {
    private final AccountRepository accountRepository;
    private final AccountOutputAssembler assembler;

    public GetAccountByIdUseCase(AccountRepository accountRepository, AccountOutputAssembler assembler) {
        this.accountRepository = accountRepository;
        this.assembler = assembler;
    }

    public AccountOutput execute(GetAccountByIdInput input) {
        var account = accountRepository.findById(UUID.fromString(input.accountId()))
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + input.accountId() + " not found"));
        return assembler.from(account);
    }
}

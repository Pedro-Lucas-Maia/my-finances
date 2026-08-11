package pedromaia.dev.myfinances.account.application.management;

import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.domain.AccountRepository;

import java.util.List;

@Service
public class ListAccountsUseCase {
    private final AccountRepository accountRepository;
    private final AccountOutputAssembler assembler;

    public ListAccountsUseCase(AccountRepository accountRepository, AccountOutputAssembler assembler) {
        this.accountRepository = accountRepository;
        this.assembler = assembler;
    }

    public List<AccountOutput> execute() {
        return accountRepository.findAll()
                .stream()
                .map(assembler::from)
                .toList();
    }
}

package pedromaia.dev.myfinances.account.application.management.account;

import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.application.management.category.GetAccountCategoryByIdInput;
import pedromaia.dev.myfinances.account.application.management.category.GetAccountCategoryByIdUseCase;
import pedromaia.dev.myfinances.account.application.ports.BankQueryPort;
import pedromaia.dev.myfinances.account.domain.AccountRepository;

import java.util.List;

@Service
public class ListAccountsUseCase {
    private final AccountRepository accountRepository;
    private final BankQueryPort port;
    private final GetAccountCategoryByIdUseCase getAccountCategoryByIdUseCase;


    public ListAccountsUseCase(AccountRepository accountRepository, BankQueryPort bankQueryPort, GetAccountCategoryByIdUseCase getAccountCategoryByIdUseCase) {
        this.accountRepository = accountRepository;
        this.port = bankQueryPort;
        this.getAccountCategoryByIdUseCase = getAccountCategoryByIdUseCase;
    }

    public List<AccountOutput> execute() {
        return accountRepository.findAll()
                .stream()
                .map(
                        account -> AccountOutput.from(
                                account,
                                getAccountCategoryByIdUseCase.execute(new GetAccountCategoryByIdInput(account.getCategoryId().uuid().toString())).name(),
                                port.getBankName(account.getBankId().uuid().toString())
                        )
                )
                .toList();
    }
}

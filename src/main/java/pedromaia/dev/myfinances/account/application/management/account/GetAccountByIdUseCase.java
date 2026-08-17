package pedromaia.dev.myfinances.account.application.management.account;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.application.management.category.GetAccountCategoryByIdInput;
import pedromaia.dev.myfinances.account.application.management.category.GetAccountCategoryByIdUseCase;
import pedromaia.dev.myfinances.account.application.ports.BankQueryPort;
import pedromaia.dev.myfinances.account.domain.AccountRepository;
import pedromaia.dev.myfinances.account.domain.exception.AccountNotFoundException;

import java.util.UUID;

@Service
public class GetAccountByIdUseCase {
    private final AccountRepository accountRepository;
    private final BankQueryPort port;
    private final GetAccountCategoryByIdUseCase getAccountCategoryByIdUseCase;


    public GetAccountByIdUseCase(AccountRepository accountRepository, BankQueryPort bankQueryPort, GetAccountCategoryByIdUseCase getAccountCategoryByIdUseCase) {
        this.accountRepository = accountRepository;
        this.port = bankQueryPort;
        this.getAccountCategoryByIdUseCase = getAccountCategoryByIdUseCase;
    }

    public AccountOutput execute(@NonNull GetAccountByIdInput input) {
        var account = accountRepository.findById(UUID.fromString(input.accountId()))
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + input.accountId() + " not found"));

        var category = getAccountCategoryByIdUseCase.execute(new GetAccountCategoryByIdInput(account.getCategoryId().uuid().toString()));

        var bankName = port.getBankName(account.getBankId().uuid().toString());

        return AccountOutput.from(
                account,
                category.name(),
                bankName
        );
    }
}

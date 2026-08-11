package pedromaia.dev.myfinances.account.application.management;

import org.springframework.stereotype.Component;
import pedromaia.dev.myfinances.account.domain.Account;
import pedromaia.dev.myfinances.account.domain.CategoryNotFoundException;
import pedromaia.dev.myfinances.account.domain.CategoryRepository;
import pedromaia.dev.myfinances.bank.domain.BankNotFoundException;
import pedromaia.dev.myfinances.bank.domain.BankRepository;

@Component
public class AccountOutputAssembler {
    private final CategoryRepository categoryRepository;
    private final BankRepository bankRepository;

    public AccountOutputAssembler(CategoryRepository categoryRepository, BankRepository bankRepository) {
        this.categoryRepository = categoryRepository;
        this.bankRepository = bankRepository;
    }

    public AccountOutput from(Account account) {
        var category = categoryRepository.findById(account.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + account.getCategoryId().uuid() + " not found"));

        var bank = bankRepository.findById(account.getBankId().uuid())
                .orElseThrow(() -> new BankNotFoundException("Bank with id " + account.getBankId().uuid() + " not found"));

        return new AccountOutput(
                account.getId().uuid().toString(),
                account.getName(),
                account.getBalance(),
                new AccountOutput.Category(category.getId().uuid().toString(), category.getName()),
                new AccountOutput.Bank(bank.getId().uuid().toString(), bank.getName())
        );
    }
}

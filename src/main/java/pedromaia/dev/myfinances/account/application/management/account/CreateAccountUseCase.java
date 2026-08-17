package pedromaia.dev.myfinances.account.application.management.account;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.domain.*;
import pedromaia.dev.myfinances.account.domain.exception.AccountAlreadyExistsException;
import pedromaia.dev.myfinances.account.domain.exception.CategoryNotFoundException;
import pedromaia.dev.myfinances.bank.domain.Bank;
import pedromaia.dev.myfinances.bank.domain.BankId;
import pedromaia.dev.myfinances.bank.domain.BankNotFoundException;
import pedromaia.dev.myfinances.bank.domain.BankRepository;

import java.util.UUID;

@Service
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final CategoryRepository categoryRepository;

    public CreateAccountUseCase(AccountRepository accountRepository, BankRepository bankRepository, CategoryRepository categoryRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @CacheEvict(value = "accounts-context", allEntries = true)
    public AccountOutput execute(@NonNull CreateAccountInput input) {
        checkAccountName(input.name());

        var bank = findBank(input.bankId());

        var category = findCategory(input.categoryId());

        Account account = accountRepository.save(new Account(
                input.name(),
                input.balance(),
                category.getId(),
                new BankId(UUID.fromString(input.bankId()))
        ));

        return AccountOutput.from(
                account,
                category.getName(),
                bank.getName()
        );
    }

    private void checkAccountName(String name) {
        if (accountRepository.existsByName(name)) {
            throw new AccountAlreadyExistsException("Account with the name " + name + " already exists");
        }
    }

    private @NonNull Bank findBank(String bankId) {
        return bankRepository.findById(UUID.fromString(bankId))
                .orElseThrow(() -> new BankNotFoundException("Bank with the id " + bankId + " not found"));
    }

    private @NonNull Category findCategory(String categoryId) {
        CategoryId id = new CategoryId(UUID.fromString(categoryId));
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with the id " + categoryId + " not found"));
    }

}

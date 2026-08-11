package pedromaia.dev.myfinances.account.application.management;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.domain.Account;
import pedromaia.dev.myfinances.account.domain.AccountAlreadyExistsException;
import pedromaia.dev.myfinances.account.domain.AccountRepository;
import pedromaia.dev.myfinances.account.domain.CategoryId;
import pedromaia.dev.myfinances.bank.domain.BankId;
import pedromaia.dev.myfinances.bank.domain.BankNotFoundException;
import pedromaia.dev.myfinances.bank.domain.BankRepository;

import java.util.UUID;

@Service
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final AccountOutputAssembler assembler;

    public CreateAccountUseCase(AccountRepository accountRepository, BankRepository bankRepository, AccountOutputAssembler assembler) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
        this.assembler = assembler;
    }

    @Transactional
    public AccountOutput execute(CreateAccountInput input) {
        checkAccountName(input.name());
        checkBankId(input.bankId());

        Account account = accountRepository.save(new Account(
                input.name(),
                input.balance(),
                new CategoryId(UUID.fromString(input.categoryId())),
                new BankId(UUID.fromString(input.bankId()))
        ));

        return assembler.from(account);
    }

    private void checkAccountName(String name) {
        if (accountRepository.existsByName(name)) {
            throw new AccountAlreadyExistsException("Account with the name " + name + " already exists");
        }
    }

    private void checkBankId(String bankId) {
        if (bankRepository.existsById(UUID.fromString(bankId))) {
            throw new BankNotFoundException("Bank with the id " + bankId + " was not found");
        }
    }
}

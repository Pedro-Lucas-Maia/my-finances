package pedromaia.dev.myfinances.bank.application;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.bank.application.input.CreateBankInput;
import pedromaia.dev.myfinances.bank.application.output.BankOutput;
import pedromaia.dev.myfinances.bank.domain.Bank;
import pedromaia.dev.myfinances.bank.domain.BankAlreadyExistsException;
import pedromaia.dev.myfinances.bank.domain.BankRepository;


@Service
public class CreateBankUseCase {
    private final BankRepository bankRepository;

    public CreateBankUseCase(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Transactional
    public BankOutput execute(@NonNull CreateBankInput input) {
        checkBankName(input.name());
        return BankOutput.from(bankRepository.save(new Bank(input.name())));
    }

    private void checkBankName(String name) {
        if (bankRepository.existsByName(name)) {
            throw new BankAlreadyExistsException("Bank with name " + name + " already exists");
        }
    }
}

package pedromaia.dev.myfinances.bank.application;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.bank.application.input.GetBankByIdInput;
import pedromaia.dev.myfinances.bank.application.output.BankOutput;
import pedromaia.dev.myfinances.bank.domain.BankNotFoundException;
import pedromaia.dev.myfinances.bank.domain.BankRepository;

import java.util.UUID;

@Service
public class GetBankByIdUseCase {
    private final BankRepository repository;

    public GetBankByIdUseCase(BankRepository bankRepository) {
        this.repository = bankRepository;
    }

    public BankOutput execute(@NonNull GetBankByIdInput input) {
        var bank = repository.findById(UUID.fromString(input.id()))
                .orElseThrow(() -> new BankNotFoundException("Bank with the id " + input.id() + " not found"));

        return BankOutput.from(bank);
    }
}

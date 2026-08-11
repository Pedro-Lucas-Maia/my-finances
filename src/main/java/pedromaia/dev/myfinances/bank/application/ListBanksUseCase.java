package pedromaia.dev.myfinances.bank.application;

import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.bank.application.output.BankOutput;
import pedromaia.dev.myfinances.bank.domain.BankRepository;

import java.util.List;

@Service
public class ListBanksUseCase {
    private final BankRepository bankRepository;

    public ListBanksUseCase(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankOutput> execute() {
        return bankRepository.findAll().stream()
                .map(BankOutput::from)
                .toList();
    }
}

package pedromaia.dev.myfinances.account.infrastructure.adapters;

import org.springframework.stereotype.Component;
import pedromaia.dev.myfinances.account.application.ports.BankQueryPort;
import pedromaia.dev.myfinances.bank.application.GetBankByIdUseCase;
import pedromaia.dev.myfinances.bank.application.input.GetBankByIdInput;

@Component
public class BankQueryAdapter implements BankQueryPort {
    private final GetBankByIdUseCase getBankByIdUseCase;

    public BankQueryAdapter(GetBankByIdUseCase getBankByIdUseCase) {
        this.getBankByIdUseCase = getBankByIdUseCase;
    }

    @Override
    public String getBankName(String bankId) {
        var bank = getBankByIdUseCase.execute(new GetBankByIdInput(bankId));

        return bank.name();
    }
}

package pedromaia.dev.myfinances.transaction.infrastructure.adapters;

import org.springframework.stereotype.Component;
import pedromaia.dev.myfinances.account.application.management.account.GetAccountByIdInput;
import pedromaia.dev.myfinances.account.application.management.account.GetAccountByIdUseCase;
import pedromaia.dev.myfinances.transaction.application.ports.AccountQueryPort;

@Component
public class GetAccountNameAdapter implements AccountQueryPort {
    private final GetAccountByIdUseCase getAccountByIdUseCase;

    public GetAccountNameAdapter(GetAccountByIdUseCase getAccountByIdUseCase) {
        this.getAccountByIdUseCase = getAccountByIdUseCase;
    }

    @Override
    public String getAccountName(String id) {
        var account = getAccountByIdUseCase.execute(new GetAccountByIdInput(id));
        return account.name();
    }
}

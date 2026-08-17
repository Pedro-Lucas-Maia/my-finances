package pedromaia.dev.myfinances.bank.infrastructure.http;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pedromaia.dev.myfinances.bank.application.CreateBankUseCase;
import pedromaia.dev.myfinances.bank.application.ListBanksUseCase;
import pedromaia.dev.myfinances.bank.infrastructure.http.request.CreateBankRequest;
import pedromaia.dev.myfinances.bank.infrastructure.http.response.BankResponse;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    private final CreateBankUseCase createBankUseCase;
    private final ListBanksUseCase listBanksUseCase;

    BankController(CreateBankUseCase createBankUseCase, ListBanksUseCase listBanksUseCase) {
        this.createBankUseCase = createBankUseCase;
        this.listBanksUseCase = listBanksUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankResponse createBank(@Valid @RequestBody CreateBankRequest createBankRequest) {
        var bank = createBankUseCase.execute(createBankRequest.toInput());
        return BankResponse.from(bank);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BankResponse> listBanks() {
        return listBanksUseCase.execute()
                .stream()
                .map(BankResponse::from)
                .toList();
    }
}

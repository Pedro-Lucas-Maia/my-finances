package pedromaia.dev.myfinances.account.infrastructure.http;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pedromaia.dev.myfinances.account.application.operations.RegisterExpenseUseCase;
import pedromaia.dev.myfinances.account.application.operations.RegisterIncomeUseCase;
import pedromaia.dev.myfinances.account.application.operations.RegisterTransferUseCase;
import pedromaia.dev.myfinances.account.infrastructure.http.request.RegisterExpenseRequest;
import pedromaia.dev.myfinances.account.infrastructure.http.request.RegisterIncomeRequest;
import pedromaia.dev.myfinances.account.infrastructure.http.request.RegisterTransferRequest;
import pedromaia.dev.myfinances.account.infrastructure.http.response.RegisterExpenseResponse;
import pedromaia.dev.myfinances.account.infrastructure.http.response.RegisterIncomeResponse;
import pedromaia.dev.myfinances.account.infrastructure.http.response.RegisterTransferResponse;

@RestController
@RequestMapping("/accounts/{accountId}")
public class AccountOperationsController {
    private final RegisterIncomeUseCase incomeUseCase;
    private final RegisterExpenseUseCase expenseUseCase;
    private final RegisterTransferUseCase transferUseCase;

    public AccountOperationsController(RegisterIncomeUseCase incomeUseCase, RegisterExpenseUseCase expenseUseCase, RegisterTransferUseCase transferUseCase) {
        this.incomeUseCase = incomeUseCase;
        this.expenseUseCase = expenseUseCase;
        this.transferUseCase = transferUseCase;
    }

    @PostMapping("/income")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterIncomeResponse registerIncome(@PathVariable @Valid String accountId, @RequestBody @Valid RegisterIncomeRequest request) {
        var output = incomeUseCase.execute(RegisterIncomeRequest.toInput(accountId, request));
        return RegisterIncomeResponse.from(output);
    }

    @PostMapping("/expense")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterExpenseResponse registerExpense(@PathVariable @Valid String accountId, @RequestBody @Valid RegisterExpenseRequest request) {
        var output = expenseUseCase.execute(RegisterExpenseRequest.toInput(accountId, request));
        return RegisterExpenseResponse.from(output);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterTransferResponse registerTransfer(@PathVariable @Valid String accountId, @RequestBody @Valid RegisterTransferRequest request) {
        var output = transferUseCase.execute(RegisterTransferRequest.toInput(accountId, request));
        return RegisterTransferResponse.from(output);
    }
}

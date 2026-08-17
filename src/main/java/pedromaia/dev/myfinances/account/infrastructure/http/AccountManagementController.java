package pedromaia.dev.myfinances.account.infrastructure.http;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pedromaia.dev.myfinances.account.application.management.account.*;
import pedromaia.dev.myfinances.account.infrastructure.http.request.CreateAccountRequest;
import pedromaia.dev.myfinances.account.infrastructure.http.response.AccountResponse;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountManagementController {
    private final CreateAccountUseCase createAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final ListAccountsUseCase listAccountsUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;

    public AccountManagementController(CreateAccountUseCase createAccountUseCase, DeleteAccountUseCase deleteAccountUseCase, ListAccountsUseCase listAccountsUseCase, GetAccountByIdUseCase getAccountByIdUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
        this.listAccountsUseCase = listAccountsUseCase;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(@RequestBody @Valid CreateAccountRequest request) {
        var output = createAccountUseCase.execute(CreateAccountRequest.toInput(request));

        return AccountResponse.from(output);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AccountResponse> findAll() {
        return listAccountsUseCase.execute()
                .stream()
                .map(AccountResponse::from)
                .toList();
    }

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse getAccountById(@PathVariable @Valid String accountId) {
        var output = getAccountByIdUseCase.execute(new GetAccountByIdInput(accountId));

        return AccountResponse.from(output);
    }

    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable @Valid String accountId) {
        deleteAccountUseCase.execute(new DeleteAccountInput(accountId));
    }

}

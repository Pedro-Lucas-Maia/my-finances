package pedromaia.dev.myfinances.account.infrastructure.http;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pedromaia.dev.myfinances.account.application.management.category.*;
import pedromaia.dev.myfinances.account.infrastructure.http.request.CreateAccountCategoryRequest;
import pedromaia.dev.myfinances.account.infrastructure.http.request.UpdateAccountCategoryRequest;
import pedromaia.dev.myfinances.account.infrastructure.http.response.AccountCategoryResponse;

import java.util.List;

@RestController
@RequestMapping("/accounts/categories")
public class AccountCategoryController {
    private final CreateAccountCategoryUseCase createAccountCategoryUseCase;
    private final GetAccountCategoryByIdUseCase getAccountCategoryByIdUseCase;
    private final ListAccountCategoriesUseCase listAccountCategoriesUseCase;
    private final DeleteAccountCategoryByIdUseCase deleteAccountCategoryByIdUseCase;
    private final UpdateAccountCategoryUseCase updateAccountCategoryUseCase;

    public AccountCategoryController(CreateAccountCategoryUseCase createAccountCategoryUseCase,
                                     GetAccountCategoryByIdUseCase getAccountCategoryByIdUseCase,
                                     ListAccountCategoriesUseCase listAccountCategoriesUseCase,
                                     DeleteAccountCategoryByIdUseCase deleteAccountCategoryByIdUseCase,
                                     UpdateAccountCategoryUseCase updateAccountCategoryUseCase) {

        this.createAccountCategoryUseCase = createAccountCategoryUseCase;
        this.getAccountCategoryByIdUseCase = getAccountCategoryByIdUseCase;
        this.listAccountCategoriesUseCase = listAccountCategoriesUseCase;
        this.deleteAccountCategoryByIdUseCase = deleteAccountCategoryByIdUseCase;
        this.updateAccountCategoryUseCase = updateAccountCategoryUseCase;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AccountCategoryResponse createAccountCategory(@Valid @RequestBody CreateAccountCategoryRequest request) {
            var output = createAccountCategoryUseCase.execute(CreateAccountCategoryRequest.toInput(request));
            return AccountCategoryResponse.from(output);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AccountCategoryResponse> listAccountCategories() {
        return listAccountCategoriesUseCase.execute()
                .stream()
                .map(AccountCategoryResponse::from)
                .toList();
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountCategoryResponse getAccountCategoryById(@PathVariable @Valid String categoryId) {
        var output = getAccountCategoryByIdUseCase.execute(new GetAccountCategoryByIdInput(categoryId));
        return AccountCategoryResponse.from(output);
    }

    @PatchMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountCategoryResponse updateAccountCategory(@PathVariable @Valid String categoryId, UpdateAccountCategoryRequest request) {
        var output = updateAccountCategoryUseCase.execute(UpdateAccountCategoryRequest.toInput(request, categoryId));
        return AccountCategoryResponse.from(output);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountCategory(@PathVariable @Valid String categoryId) {
        deleteAccountCategoryByIdUseCase.execute(new DeleteAccountCategoryByIdInput(categoryId));
    }
}


package pedromaia.dev.myfinances.transaction.infrastructure.http;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pedromaia.dev.myfinances.account.application.management.category.DeleteAccountCategoryByIdInput;
import pedromaia.dev.myfinances.account.application.management.category.DeleteAccountCategoryByIdUseCase;
import pedromaia.dev.myfinances.transaction.application.category.*;
import pedromaia.dev.myfinances.transaction.infrastructure.http.request.CreateTransactionCategoryRequest;
import pedromaia.dev.myfinances.transaction.infrastructure.http.request.UpdateTransactionCategoryRequest;
import pedromaia.dev.myfinances.transaction.infrastructure.http.response.TransactionCategoryResponse;

import java.util.List;

@RestController
@RequestMapping("/transactions/categories")
public class TransactionCategoryController {
    private final CreateTransactionCategoryUseCase createTransactionCategoryUseCase;
    private final GetTransactionCategoryByIdUseCase getTransactionCategoryByIdUseCase;
    private final ListTransactionCategoriesUseCase listTransactionCategoriesUseCase;
    private final UpdateTransactionCategoryUseCase updateTransactionCategoryUseCase;
    private final DeleteAccountCategoryByIdUseCase deleteAccountCategoryByIdUseCase;

    public TransactionCategoryController(CreateTransactionCategoryUseCase createTransactionCategoryUseCase,
                                         GetTransactionCategoryByIdUseCase getTransactionCategoryByIdUseCase,
                                         ListTransactionCategoriesUseCase listTransactionCategoriesUseCase,
                                         UpdateTransactionCategoryUseCase updateTransactionCategoryUseCase,
                                         DeleteAccountCategoryByIdUseCase deleteAccountCategoryByIdUseCase) {
        this.createTransactionCategoryUseCase = createTransactionCategoryUseCase;
        this.getTransactionCategoryByIdUseCase = getTransactionCategoryByIdUseCase;
        this.listTransactionCategoriesUseCase = listTransactionCategoriesUseCase;
        this.updateTransactionCategoryUseCase = updateTransactionCategoryUseCase;
        this.deleteAccountCategoryByIdUseCase = deleteAccountCategoryByIdUseCase;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionCategoryResponse createTransactionCategory(@RequestBody @Valid CreateTransactionCategoryRequest request) {
        var output = createTransactionCategoryUseCase.execute(CreateTransactionCategoryRequest.toInput(request));
        return TransactionCategoryResponse.from(output);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionCategoryResponse> listTransactionCategories() {
        return listTransactionCategoriesUseCase.execute()
                .stream()
                .map(TransactionCategoryResponse::from)
                .toList();
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionCategoryResponse getTransactionCategoryById(@PathVariable @Valid String categoryId) {
        var output = getTransactionCategoryByIdUseCase.execute(new GetTransactionCategoryByIdInput(categoryId));
        return TransactionCategoryResponse.from(output);
    }

    @PatchMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionCategoryResponse updateTransactionCategory(@PathVariable @Valid String categoryId, @Valid @RequestBody UpdateTransactionCategoryRequest request) {
        var output = updateTransactionCategoryUseCase.execute(UpdateTransactionCategoryRequest.toInput(request, categoryId));
        return TransactionCategoryResponse.from(output);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransactionCategory(@PathVariable @Valid String categoryId) {
        deleteAccountCategoryByIdUseCase.execute(new DeleteAccountCategoryByIdInput(categoryId));
    }
}

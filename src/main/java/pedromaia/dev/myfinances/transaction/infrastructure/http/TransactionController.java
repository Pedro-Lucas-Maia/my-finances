package pedromaia.dev.myfinances.transaction.infrastructure.http;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pedromaia.dev.myfinances.transaction.application.GetTransactionByIdInput;
import pedromaia.dev.myfinances.transaction.application.GetTransactionByIdUseCase;
import pedromaia.dev.myfinances.transaction.application.ListTransactionsInput;
import pedromaia.dev.myfinances.transaction.application.ListTransactionsUseCase;
import pedromaia.dev.myfinances.transaction.infrastructure.http.response.TransactionResponse;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final GetTransactionByIdUseCase getTransactionByIdUseCase;
    private final ListTransactionsUseCase listTransactionsUseCase;

    public TransactionController(GetTransactionByIdUseCase getTransactionByIdUseCase, ListTransactionsUseCase listTransactionsUseCase) {
        this.getTransactionByIdUseCase = getTransactionByIdUseCase;
        this.listTransactionsUseCase = listTransactionsUseCase;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponse> getAllTransactions(
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return listTransactionsUseCase.execute(new ListTransactionsInput(categoryId, type, date))
                .stream()
                .map(TransactionResponse::from)
                .toList();
    }

    @GetMapping("/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse getTransactionById(@PathVariable @Valid String transactionId) {
        return TransactionResponse.from(getTransactionByIdUseCase.execute(new GetTransactionByIdInput(transactionId)));
    }
}

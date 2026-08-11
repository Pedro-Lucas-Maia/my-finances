package pedromaia.dev.myfinances.transaction.infrastructure.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pedromaia.dev.myfinances.transaction.application.GetTransactionByIdUseCase;
import pedromaia.dev.myfinances.transaction.application.ListTransactionsUseCase;
import pedromaia.dev.myfinances.transaction.application.input.GetTransactionByIdInput;
import pedromaia.dev.myfinances.transaction.application.output.TransactionOutput;
import pedromaia.dev.myfinances.transaction.infrastructure.http.response.TransactionResponse;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final GetTransactionByIdUseCase getTransactionByIdUseCase;
    private final ListTransactionsUseCase listTransactionsUseCase;

    public TransactionController(GetTransactionByIdUseCase getTransactionByIdUseCase, ListTransactionsUseCase listTransactionsUseCase) {
        this.getTransactionByIdUseCase = getTransactionByIdUseCase;
        this.listTransactionsUseCase = listTransactionsUseCase;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponse> getAllTransactions() {
        return listTransactionsUseCase.execute()
                .stream()
                .map(TransactionResponse::from)
                .toList();
    }

    @GetMapping("/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse getTransactionById(@PathVariable String transactionId) {
        return TransactionResponse.from(getTransactionByIdUseCase.execute(new GetTransactionByIdInput(transactionId)));
    }
}

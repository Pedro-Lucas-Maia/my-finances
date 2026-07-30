package pedromaia.dev.myfinances.application;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.application.input.PersistTransactionInput;
import pedromaia.dev.myfinances.application.output.TransactionOutput;
import pedromaia.dev.myfinances.domain.Transaction;
import pedromaia.dev.myfinances.domain.TransactionRepository;

@Service
public class PersistTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public PersistTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "persist-transaction", description = "Persiste uma nova transação financeira")
    public TransactionOutput execute(PersistTransactionInput input) {
        var transaction = transactionRepository.save(
                new Transaction(input.description(), input.amount(), input.category()));

        return TransactionOutput.from(transaction);
    }
}

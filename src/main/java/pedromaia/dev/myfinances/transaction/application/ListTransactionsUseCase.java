package pedromaia.dev.myfinances.transaction.application;

import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.transaction.application.output.TransactionOutput;
import pedromaia.dev.myfinances.transaction.application.output.TransactionOutputAssembler;
import pedromaia.dev.myfinances.transaction.domain.TransactionRepository;

import java.util.List;

@Service
public class ListTransactionsUseCase {
    private final TransactionRepository repository;
    private final TransactionOutputAssembler assembler;

    public ListTransactionsUseCase(TransactionRepository repository, TransactionOutputAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public List<TransactionOutput> execute() {
        var transactions = repository.findAll();

        return transactions
                .stream()
                .map(assembler::from)
                .toList();
    }
}

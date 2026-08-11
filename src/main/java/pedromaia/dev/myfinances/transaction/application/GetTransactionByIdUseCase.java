package pedromaia.dev.myfinances.transaction.application;

import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.transaction.application.input.GetTransactionByIdInput;
import pedromaia.dev.myfinances.transaction.application.output.TransactionOutput;
import pedromaia.dev.myfinances.transaction.application.output.TransactionOutputAssembler;
import pedromaia.dev.myfinances.transaction.domain.TransactionNotFoundException;
import pedromaia.dev.myfinances.transaction.domain.TransactionRepository;

import java.util.UUID;

@Service
public class GetTransactionByIdUseCase {
    private final TransactionRepository repository;
    private final TransactionOutputAssembler assembler;
    public GetTransactionByIdUseCase(TransactionRepository repository, TransactionOutputAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public TransactionOutput execute(GetTransactionByIdInput input) {
        var transaction = repository.findById(UUID.fromString(input.uuid()))
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id " + input.uuid() + " not found"));

        return assembler.from(transaction);
    }
}

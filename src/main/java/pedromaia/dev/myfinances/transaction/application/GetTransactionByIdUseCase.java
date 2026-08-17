package pedromaia.dev.myfinances.transaction.application;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.transaction.domain.TransactionRepository;
import pedromaia.dev.myfinances.transaction.domain.exception.TransactionNotFoundException;

import java.util.UUID;

@Service
public class GetTransactionByIdUseCase {
    private final TransactionRepository repository;
    private final TransactionOutputMapper mapper;

    public GetTransactionByIdUseCase(TransactionRepository repository, TransactionOutputMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TransactionOutput execute(@NonNull GetTransactionByIdInput input) {
        var transaction = repository.findById(UUID.fromString(input.uuid()))
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id " + input.uuid() + " not found"));

        return mapper.toOutput(transaction);
    }
}

package pedromaia.dev.myfinances.transaction.application;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.common.domain.exceptions.DomainException;
import pedromaia.dev.myfinances.transaction.domain.TransactionRepository;
import pedromaia.dev.myfinances.transaction.domain.Type;
import pedromaia.dev.myfinances.transaction.infrastructure.persistence.entity.TransactionEntity;
import pedromaia.dev.myfinances.transaction.infrastructure.persistence.repository.TransactionSpecs;

import java.util.List;

@Service
public class ListTransactionsUseCase {
    private final TransactionRepository repository;
    private final TransactionOutputMapper mapper;

    public ListTransactionsUseCase(TransactionRepository repository, TransactionOutputMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TransactionOutput> execute(ListTransactionsInput input) {
        Type typeEnum = checkType(input.type());

        Specification<TransactionEntity> spec = Specification
                .where(TransactionSpecs.byCategory(input.categoryId()))
                .or(TransactionSpecs.byType(typeEnum))
                .or(TransactionSpecs.byLast30days(input.referenceDate()));

        return repository.findAll(spec)
                .stream()
                .map(mapper::toOutput)
                .toList();
    }
    private @Nullable Type checkType(String type) {
        try {
            return type != null && !type.isBlank()
                    ? Type.valueOf(type.trim().toUpperCase())
                    : null;
        } catch (IllegalArgumentException e) {
            throw new DomainException("Enum type not found");
        }
    }
}


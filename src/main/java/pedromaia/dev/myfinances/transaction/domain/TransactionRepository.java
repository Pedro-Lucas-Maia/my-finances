package pedromaia.dev.myfinances.transaction.domain;

import org.springframework.data.jpa.domain.Specification;
import pedromaia.dev.myfinances.transaction.infrastructure.persistence.entity.TransactionEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(UUID uuid);
    List<Transaction> findAll(Specification<TransactionEntity> spec);
    List<Transaction> findAllByCategoryId(String categoryId);
}

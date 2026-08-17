package pedromaia.dev.myfinances.transaction.infrastructure.persistence.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.repository.CrudRepository;
import pedromaia.dev.myfinances.transaction.infrastructure.persistence.entity.TransactionCategoryEntity;

import java.util.List;
import java.util.UUID;

public interface TransactionCategoryEntityRepository extends CrudRepository<TransactionCategoryEntity, UUID> {
    @NonNull List<TransactionCategoryEntity> findAll();
    boolean existsByName(String name);
}

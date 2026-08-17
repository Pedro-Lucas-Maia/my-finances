package pedromaia.dev.myfinances.transaction.infrastructure.persistence.repository;


import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import pedromaia.dev.myfinances.transaction.infrastructure.persistence.entity.TransactionEntity;

import java.util.List;
import java.util.UUID;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID>, JpaSpecificationExecutor<TransactionEntity> {
    @NonNull List<TransactionEntity> findAll();
    List<TransactionEntity> findAllByCategoryId(UUID categoryId);
}

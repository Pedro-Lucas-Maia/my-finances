package pedromaia.dev.myfinances.infrastructure.persistence.repository;


import org.springframework.data.repository.CrudRepository;
import pedromaia.dev.myfinances.domain.Category;
import pedromaia.dev.myfinances.infrastructure.persistence.entity.TransactionEntity;

import java.util.List;
import java.util.UUID;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findAllByCategory(Category category);
}

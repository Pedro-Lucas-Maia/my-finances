package pedromaia.dev.myfinances.account.infrastructure.persistence.repository;


import org.jspecify.annotations.NonNull;
import org.springframework.data.repository.CrudRepository;
import pedromaia.dev.myfinances.account.infrastructure.persistence.entity.AccountCategoryEntity;

import java.util.List;
import java.util.UUID;

public interface AccountCategoryEntityRepository extends CrudRepository<AccountCategoryEntity, UUID> {
    @NonNull List<AccountCategoryEntity> findAll();
    boolean existsByName(String name);
}

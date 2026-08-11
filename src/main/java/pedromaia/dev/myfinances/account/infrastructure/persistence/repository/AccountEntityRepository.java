package pedromaia.dev.myfinances.account.infrastructure.persistence.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.repository.CrudRepository;
import pedromaia.dev.myfinances.account.infrastructure.persistence.entity.AccountEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountEntityRepository extends CrudRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findByCategoryId(UUID categoryId);
    @NonNull List<AccountEntity> findAll();
    boolean existsByName(String name);
}

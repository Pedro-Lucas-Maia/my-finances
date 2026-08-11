package pedromaia.dev.myfinances.bank.infrastructure.persistence.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.repository.CrudRepository;
import pedromaia.dev.myfinances.bank.infrastructure.persistence.entity.BankEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankEntityRepository extends CrudRepository<BankEntity, UUID> {
    Optional<BankEntity> findByName(String name);
    @NonNull List<BankEntity> findAll();
    boolean existsByName(String name);

    UUID id(UUID id);
}

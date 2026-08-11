package pedromaia.dev.myfinances.account.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import pedromaia.dev.myfinances.account.domain.Account;
import pedromaia.dev.myfinances.account.domain.AccountRepository;
import pedromaia.dev.myfinances.account.domain.CategoryId;
import pedromaia.dev.myfinances.account.infrastructure.persistence.entity.AccountEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaAccountRepository implements AccountRepository {
    private final AccountEntityRepository repository;

    public JpaAccountRepository(AccountEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(Account account) {
        return AccountEntity.toDomain(repository.save(AccountEntity.from(account)));
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return repository.findById(id)
                .map(AccountEntity::toDomain);

    }

    @Override
    public List<Account> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountEntity::toDomain)
                .toList();
    }

    @Override
    public List<Account> findByCategoryId(CategoryId categoryId) {
        return repository.findByCategoryId(categoryId.uuid())
                .stream()
                .map(AccountEntity::toDomain)
                .toList();
    }

    @Override
    public void delete(Account account) {
        repository.delete(AccountEntity.from(account));
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}

package pedromaia.dev.myfinances.transaction.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import pedromaia.dev.myfinances.transaction.domain.Category;
import pedromaia.dev.myfinances.transaction.domain.CategoryRepository;
import pedromaia.dev.myfinances.transaction.infrastructure.persistence.entity.TransactionCategoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaTransactionCategoryRepository implements CategoryRepository {
    private final TransactionCategoryEntityRepository repository;

    public JpaTransactionCategoryRepository(TransactionCategoryEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category save(Category category) {
        var entity = TransactionCategoryEntity.from(category);
        return TransactionCategoryEntity.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Category> findById(UUID uuid) {
        return repository.findById(uuid)
                .map(TransactionCategoryEntity::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll()
                .stream()
                .map(TransactionCategoryEntity::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }
}

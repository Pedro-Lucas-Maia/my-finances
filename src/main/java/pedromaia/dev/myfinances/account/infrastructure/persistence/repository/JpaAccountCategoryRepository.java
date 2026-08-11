package pedromaia.dev.myfinances.account.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import pedromaia.dev.myfinances.account.domain.Category;
import pedromaia.dev.myfinances.account.domain.CategoryId;
import pedromaia.dev.myfinances.account.domain.CategoryRepository;
import pedromaia.dev.myfinances.account.infrastructure.persistence.entity.AccountCategoryEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaAccountCategoryRepository implements CategoryRepository {
    private final AccountCategoryEntityRepository repository;

    public JpaAccountCategoryRepository(AccountCategoryEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category save(Category category) {
        return AccountCategoryEntity.toDomain(repository.save(AccountCategoryEntity.from(category)));
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountCategoryEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Category> findById(CategoryId id) {
        return repository.findById(id.uuid())
                .map(AccountCategoryEntity::toDomain);
    }

    @Override
    public void deleteById(CategoryId id) {
        repository.deleteById(id.uuid());
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}

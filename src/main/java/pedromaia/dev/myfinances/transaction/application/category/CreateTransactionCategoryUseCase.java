package pedromaia.dev.myfinances.transaction.application.category;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.transaction.domain.Category;
import pedromaia.dev.myfinances.transaction.domain.CategoryRepository;
import pedromaia.dev.myfinances.transaction.domain.exception.CategoryTransactionAlreadyExistsException;

@Service
public class CreateTransactionCategoryUseCase {
    private final CategoryRepository repository;

    public CreateTransactionCategoryUseCase(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    @Transactional
    @CacheEvict(value = "categories-context", allEntries = true)
    public TransactionCategoryOutput execute(@NonNull CreateTransactionCategoryInput input) {
        checkName(input.name());
        var category = new Category(input.name());
        return TransactionCategoryOutput.from(repository.save(category));
    }

    private void checkName(String name) {
        if (repository.existsByName(name)) {
            throw new CategoryTransactionAlreadyExistsException("Transaction Category name " + name + " already exists");
        }
    }
}

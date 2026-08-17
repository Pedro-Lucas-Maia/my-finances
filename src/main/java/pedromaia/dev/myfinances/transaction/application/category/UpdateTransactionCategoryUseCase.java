package pedromaia.dev.myfinances.transaction.application.category;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.transaction.domain.CategoryRepository;
import pedromaia.dev.myfinances.transaction.domain.exception.CategoryNotFoundException;
import pedromaia.dev.myfinances.transaction.domain.exception.CategoryTransactionAlreadyExistsException;

import java.util.UUID;

@Service
public class UpdateTransactionCategoryUseCase {
    private final CategoryRepository repository;

    public UpdateTransactionCategoryUseCase(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    @Transactional
    public TransactionCategoryOutput execute(@NonNull UpdateTransactionCategoryInput input) {
        checkName(input.name());
        var category = repository.findById(UUID.fromString(input.id()))
                .orElseThrow(() -> new CategoryNotFoundException("Category with the id" + input.id() + " not found"));
        var updatedCategory = category.updateName(input.name());

        return TransactionCategoryOutput.from(repository.save(updatedCategory));
    }

    private void checkName(String name) {
        if (repository.existsByName(name)) {
            throw new CategoryTransactionAlreadyExistsException("Category transaction with name " + name + " already exists");
        }
    }
}

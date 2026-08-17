package pedromaia.dev.myfinances.account.application.management.category;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.domain.CategoryId;
import pedromaia.dev.myfinances.account.domain.CategoryRepository;
import pedromaia.dev.myfinances.account.domain.exception.CategoryAlreadyExistsException;
import pedromaia.dev.myfinances.account.domain.exception.CategoryNotFoundException;

import java.util.UUID;

@Service
public class UpdateAccountCategoryUseCase {
    private final CategoryRepository repository;

    public UpdateAccountCategoryUseCase(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    @Transactional
    public AccountCategoryOutput execute(@NonNull UpdateAccountCategoryInput input) {
        checkName(input.name());
        var category = repository.findById(new CategoryId(UUID.fromString(input.id())))
                .orElseThrow(() -> new CategoryNotFoundException("Account Category with the id " + input.id() + " not found"));

        var updatedCategory = category.updateName(input.name());
        return AccountCategoryOutput.from(repository.save(updatedCategory));
    }

    private void checkName(String name) {
        if (repository.existsByName(name)) {
            throw new CategoryAlreadyExistsException("Account Category with the name " + name + " already exists");
        }
    }
}

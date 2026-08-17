package pedromaia.dev.myfinances.account.application.management.category;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.domain.CategoryId;
import pedromaia.dev.myfinances.account.domain.CategoryRepository;
import pedromaia.dev.myfinances.account.domain.exception.CategoryNotFoundException;

import java.util.UUID;

@Service
public class GetAccountCategoryByIdUseCase {
    private final CategoryRepository repository;

    public GetAccountCategoryByIdUseCase(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    public AccountCategoryOutput execute(@NonNull GetAccountCategoryByIdInput input) {
        var category = repository.findById(new CategoryId(UUID.fromString(input.id())))
                .orElseThrow(() -> new CategoryNotFoundException("Category with the id " + input.id() + " not found"));

        return AccountCategoryOutput.from(category);
    }
}

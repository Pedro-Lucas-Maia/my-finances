package pedromaia.dev.myfinances.account.application.management.category;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.domain.Category;
import pedromaia.dev.myfinances.account.domain.CategoryRepository;
import pedromaia.dev.myfinances.account.domain.exception.CategoryAlreadyExistsException;

@Service
public class CreateAccountCategoryUseCase {
    private final CategoryRepository repository;

    public CreateAccountCategoryUseCase(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    @Transactional
    public AccountCategoryOutput execute(@NonNull CreateAccountCategoryInput input) {
       checkName(input.name());

        return AccountCategoryOutput.from(repository.save(new Category(input.name())));
    }

    private void checkName(String name) {
        if(repository.existsByName(name)) {
            throw new CategoryAlreadyExistsException("Account Category with name " + name + " already exists");
        }
    }
}

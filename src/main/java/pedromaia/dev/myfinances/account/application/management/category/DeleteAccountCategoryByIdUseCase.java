package pedromaia.dev.myfinances.account.application.management.category;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.domain.AccountRepository;
import pedromaia.dev.myfinances.account.domain.CategoryId;
import pedromaia.dev.myfinances.account.domain.CategoryRepository;
import pedromaia.dev.myfinances.account.domain.exception.CategoryNotDeletableException;
import pedromaia.dev.myfinances.account.domain.exception.CategoryNotFoundException;

import java.util.UUID;

@Service
public class DeleteAccountCategoryByIdUseCase {
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;

    public DeleteAccountCategoryByIdUseCase(CategoryRepository categoryRepository, AccountRepository accountRepository) {
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void execute(@NonNull DeleteAccountCategoryByIdInput input) {
        var categoryId = new CategoryId(UUID.fromString(input.id()));
        var category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category with the id " + input.id() + " not found"));
        var transactions = accountRepository.findAllByCategoryId(categoryId);
        if (transactions.isEmpty()) {
            categoryRepository.delete(category);
        } else {
            throw new CategoryNotDeletableException("Can't delete category with the id "+ input.id() + ", it has accounts on that category");
        }
    }
}

package pedromaia.dev.myfinances.transaction.application.category;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.transaction.domain.CategoryRepository;
import pedromaia.dev.myfinances.transaction.domain.TransactionRepository;
import pedromaia.dev.myfinances.transaction.domain.exception.CategoryNotDeletableException;
import pedromaia.dev.myfinances.transaction.domain.exception.CategoryNotFoundException;

import java.util.UUID;

@Service
public class DeleteTransactionCategoryUseCase {
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public DeleteTransactionCategoryUseCase(CategoryRepository categoryRepository, TransactionRepository transactionRepository) {
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    @CacheEvict(value = "categories-context", allEntries = true)
    public void execute(@NonNull DeleteTransactionCategoryInput input) {
        var category = categoryRepository.findById(UUID.fromString(input.id()))
                .orElseThrow(() -> new CategoryNotFoundException("Transaction Category with the id " + input.id() + " not found"));

        var transactions = transactionRepository.findAllByCategoryId(input.id());
        if (transactions.isEmpty()) {
            categoryRepository.delete(category);
        } else {
            throw new CategoryNotDeletableException("Can't delete category with the id" + input.id() + ", it has accounts on that category");
        }
    }
}

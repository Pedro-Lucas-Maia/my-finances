package pedromaia.dev.myfinances.transaction.application.category;

import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.transaction.domain.CategoryRepository;
import pedromaia.dev.myfinances.transaction.domain.exception.CategoryNotFoundException;

import java.util.UUID;

@Service
public class GetTransactionCategoryByIdUseCase {
    private final CategoryRepository repository;

    public GetTransactionCategoryByIdUseCase(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    public TransactionCategoryOutput execute(GetTransactionCategoryByIdInput input) {
        var category = repository.findById(UUID.fromString(input.id()))
                .orElseThrow(() -> new CategoryNotFoundException("Transaction Category with id " + input.id() + " not found"));
        return TransactionCategoryOutput.from(category);
    }
}

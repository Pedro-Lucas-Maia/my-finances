package pedromaia.dev.myfinances.transaction.application.category;

import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.transaction.domain.CategoryRepository;

import java.util.List;

@Service
public class ListTransactionCategoriesUseCase {
    private final CategoryRepository categoryRepository;

    public ListTransactionCategoriesUseCase(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

    public List<TransactionCategoryOutput> execute() {
        return this.categoryRepository.findAll()
                .stream()
                .map(TransactionCategoryOutput::from)
                .toList();
    }
}

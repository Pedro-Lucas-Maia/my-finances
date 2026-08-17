package pedromaia.dev.myfinances.account.application.management.category;

import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.domain.CategoryRepository;

import java.util.List;

@Service
public class ListAccountCategoriesUseCase {
    private final CategoryRepository categoryRepository;

    public ListAccountCategoriesUseCase(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

    public List<AccountCategoryOutput> execute() {
        return this.categoryRepository.findAll()
                .stream()
                .map(AccountCategoryOutput::from)
                .toList();
    }
}

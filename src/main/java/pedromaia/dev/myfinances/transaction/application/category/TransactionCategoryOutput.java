package pedromaia.dev.myfinances.transaction.application.category;

import pedromaia.dev.myfinances.transaction.domain.Category;

public record TransactionCategoryOutput(
        String categoryId,
        String name
) {
    public static TransactionCategoryOutput from(Category category) {
        return new TransactionCategoryOutput(
                category.getCategoryId().uuid().toString(),
                category.getName()
        );
    }
}

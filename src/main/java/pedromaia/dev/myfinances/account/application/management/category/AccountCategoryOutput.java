package pedromaia.dev.myfinances.account.application.management.category;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.account.domain.Category;

public record AccountCategoryOutput(
    String categoryId,
    String name
) {
    public static AccountCategoryOutput from(@NonNull Category category) {
        return new AccountCategoryOutput(
                category.getId().uuid().toString(),
                category.getName()
        );
    }
}

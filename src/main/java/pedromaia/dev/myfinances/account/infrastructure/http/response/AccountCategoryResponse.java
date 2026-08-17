package pedromaia.dev.myfinances.account.infrastructure.http.response;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.account.application.management.category.AccountCategoryOutput;

public record AccountCategoryResponse(
        String id,
        String name
) {
    public static AccountCategoryResponse from(@NonNull AccountCategoryOutput output) {
        return new AccountCategoryResponse(
                output.categoryId(),
                output.name()
        );
    }
}

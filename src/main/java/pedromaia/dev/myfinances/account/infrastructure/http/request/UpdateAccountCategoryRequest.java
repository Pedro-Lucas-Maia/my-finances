package pedromaia.dev.myfinances.account.infrastructure.http.request;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.account.application.management.category.UpdateAccountCategoryInput;

public record UpdateAccountCategoryRequest(
        String name
) {
    public static UpdateAccountCategoryInput toInput(@NonNull UpdateAccountCategoryRequest request, String categoryId) {
        return new UpdateAccountCategoryInput(
                categoryId,
                request.name()
        );
    }
}

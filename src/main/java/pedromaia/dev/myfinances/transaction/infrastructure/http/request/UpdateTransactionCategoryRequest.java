package pedromaia.dev.myfinances.transaction.infrastructure.http.request;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.transaction.application.category.UpdateTransactionCategoryInput;

public record UpdateTransactionCategoryRequest(
        String name
) {
    public static UpdateTransactionCategoryInput toInput(@NonNull UpdateTransactionCategoryRequest request, @NonNull String categoryId) {
        return new UpdateTransactionCategoryInput(
                categoryId,
                request.name
        );
    }
}

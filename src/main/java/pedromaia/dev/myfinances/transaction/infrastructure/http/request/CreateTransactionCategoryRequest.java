package pedromaia.dev.myfinances.transaction.infrastructure.http.request;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.transaction.application.category.CreateTransactionCategoryInput;

public record CreateTransactionCategoryRequest(
        String name
) {
    public static @NonNull CreateTransactionCategoryInput toInput(@NonNull CreateTransactionCategoryRequest request) {
        return new CreateTransactionCategoryInput(
                request.name
        );
    }
}

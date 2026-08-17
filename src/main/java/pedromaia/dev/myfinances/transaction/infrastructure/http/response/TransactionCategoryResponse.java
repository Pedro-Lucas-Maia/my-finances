package pedromaia.dev.myfinances.transaction.infrastructure.http.response;

import pedromaia.dev.myfinances.transaction.application.category.TransactionCategoryOutput;

public record TransactionCategoryResponse(
        String id,
        String name
) {
    public static TransactionCategoryResponse from(TransactionCategoryOutput output) {
        return new TransactionCategoryResponse(
                output.categoryId(),
                output.name()
        );
    }
}

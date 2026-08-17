package pedromaia.dev.myfinances.transaction.application.category;

import org.hibernate.validator.constraints.UUID;

public record DeleteTransactionCategoryInput(
        @UUID(message = "Transaction Category id must be a valid UUID")
        String id
) {
}

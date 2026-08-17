package pedromaia.dev.myfinances.transaction.application.category;

import org.hibernate.validator.constraints.UUID;

public record GetTransactionCategoryByIdInput(
        @UUID(message =  "Transaction Category ID must be a valid UUID")
        String id
) {
}

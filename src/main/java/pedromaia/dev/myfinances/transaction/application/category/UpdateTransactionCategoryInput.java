package pedromaia.dev.myfinances.transaction.application.category;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UUID;

public record UpdateTransactionCategoryInput(
        @UUID(message = "Transaction Category ID must be a valid UUID")
        String id,

        @NotBlank(message = "Transaction Category name can't be blank")
        String name
) {
}

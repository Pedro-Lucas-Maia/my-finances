package pedromaia.dev.myfinances.account.application.management.category;

import org.hibernate.validator.constraints.UUID;

public record DeleteAccountCategoryByIdInput(
        @UUID(message = "Category id must be a valid UUID", allowNil = false)
        String id
) {
}

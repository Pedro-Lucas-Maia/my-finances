package pedromaia.dev.myfinances.transaction.application.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;
import pedromaia.dev.myfinances.transaction.domain.Category;
import pedromaia.dev.myfinances.transaction.domain.Type;

public record PersistTransactionInput(
        String description,

        @NotNull(message = "Transaction amount cannot be null")
        @Positive(message = "Transaction amount must be positive")
        long amount,

        @NotNull(message = "Transaction categoryId cannot be null")
        Category category,

        @NotNull(message = "Transaction type cannot be null")
        Type type,

        @UUID(message = "Transaction account receiver ID must be a valid UUID")
        String accountReceiverId,

        @UUID(message = "Transaction account sender ID must be a valid UUID")
        String accountSenderId,

        @UUID(message = "Transaction statement ID must be a valid UUID")
        String statementId
                                      ) {
}

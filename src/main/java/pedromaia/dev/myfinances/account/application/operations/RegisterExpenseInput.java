package pedromaia.dev.myfinances.account.application.operations;

import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

public record RegisterExpenseInput(
        String description,

        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @UUID(message = "Category ID must be a valid UUID", allowNil = false)
        String categoryId,

        @UUID(message = "Account ID must be a valid UUID", allowNil = false)
        String accountSenderId
) {
}

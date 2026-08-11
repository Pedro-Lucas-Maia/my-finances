package pedromaia.dev.myfinances.account.application.operations;

import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

public record RegisterIncomeInput(
        String description,

        @Positive(message = "Income amount must be positive")
        BigDecimal amount,

        @UUID(message = "Category ID must be a valid UUID", allowNil = false)
        String categoryId,

        @UUID(message = "accountReceiver ID must be a valid UUID", allowNil = false)
        String accountReceiverId
) {
}

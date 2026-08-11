package pedromaia.dev.myfinances.account.application.operations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

public record RegisterTransferInput(
        @NotBlank(message = "Description must not be blank")
        String description,

        @Positive(message = "Amount to be transferred must be positive")
        BigDecimal amount,

        @UUID(message = "Category ID must be a valid UUID", allowNil = false)
        String categoryId,

        @UUID(message = "Receiver's account ID must be a valid UUID", allowNil = false)
        String accountReceiverId,

        @UUID(message = "Sender's account ID must be a valid UUID", allowNil = false)
        String accountSenderId
) {
}

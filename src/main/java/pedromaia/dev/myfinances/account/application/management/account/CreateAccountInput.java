package pedromaia.dev.myfinances.account.application.management.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

public record CreateAccountInput(
        @Length(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
        @NotBlank(message = "Account name can't be blank")
        String name,

        @PositiveOrZero(message = "Balance must be positive or zero")
        BigDecimal balance,

        @UUID(message = "Category ID must be a valid UUID", allowNil = false)
        @NotNull(message = "Bank ID can't be null")
        String categoryId,

        @UUID(message = "Bank ID must be a valid UUID", allowNil = false)
        @NotNull(message = "Bank ID can't be null")
        String bankId
) {
}

package pedromaia.dev.myfinances.transaction.application.category;

import jakarta.validation.constraints.NotBlank;

public record CreateTransactionCategoryInput(
        @NotBlank(message = "Transaction Category can't be blank")
        String name
) {
}

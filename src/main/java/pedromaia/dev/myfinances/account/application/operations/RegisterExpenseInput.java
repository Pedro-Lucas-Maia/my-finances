package pedromaia.dev.myfinances.account.application.operations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;
import org.springframework.ai.tool.annotation.ToolParam;

import java.math.BigDecimal;

public record RegisterExpenseInput(
        @ToolParam(description = "Descrição do gasto")
        @NotBlank(message = "Expense description can't be blank")
        String description,

        @ToolParam(description = "Valor gasto (sempre positivo e diferente de 0.0)")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @ToolParam(description = "Categoria de uma transação, deve ser um UUID válido")
        @UUID(message = "Category ID must be a valid UUID", allowNil = false)
        String categoryId,

        @ToolParam(description = "Id da conta que está sendo registrada a despesa, deve ser um UUID válido")
        @UUID(message = "Account ID must be a valid UUID", allowNil = false)
        String accountSenderId
) {
}

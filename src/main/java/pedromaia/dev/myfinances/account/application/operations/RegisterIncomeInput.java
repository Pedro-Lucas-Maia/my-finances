package pedromaia.dev.myfinances.account.application.operations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;
import org.springframework.ai.tool.annotation.ToolParam;

import java.math.BigDecimal;

public record RegisterIncomeInput(
        @ToolParam(description = "Descrição da receita/ganho recebido")
        @NotBlank(message = "Income description can't be blank")
        String description,

        @ToolParam(description = "Valor recebido, valor que vai entrar na conta, precisa ser positivo e diferente de 0")
        @Positive(message = "Income amount must be positive")
        BigDecimal amount,

        @ToolParam(description = "Categoria da transação, tem que ser um UUID válido")
        @UUID(message = "Category ID must be a valid UUID", allowNil = false)
        String categoryId,

        @ToolParam(description = "Conta que está recebendo o dinheiro/está registrando a transação, tem que ser um UUID válido")
        @UUID(message = "accountReceiver ID must be a valid UUID", allowNil = false)
        String accountReceiverId
) {
}

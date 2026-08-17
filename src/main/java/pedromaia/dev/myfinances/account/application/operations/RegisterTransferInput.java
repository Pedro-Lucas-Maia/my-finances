package pedromaia.dev.myfinances.account.application.operations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;
import org.springframework.ai.tool.annotation.ToolParam;

import java.math.BigDecimal;

public record RegisterTransferInput(
        @ToolParam(description = "Descrição da transação")
        @NotBlank(message = "Description must not be blank")
        String description,

        @ToolParam(description = "Valor que está sendo transferido, precisa ser positivo e diferente de zero")
        @Positive(message = "Amount to be transferred must be positive")
        BigDecimal amount,

        @ToolParam(description = "Categoria da transação, precisa ser um UUID válido")
        @UUID(message = "Category ID must be a valid UUID", allowNil = false)
        String categoryId,

        @ToolParam(description = "ID da conta que vai receber o dinheiro, precisa ser um UUID válido")
        @UUID(message = "Receiver's account ID must be a valid UUID", allowNil = false)
        String accountReceiverId,

        @ToolParam(description = "ID da conta a qual vai sair o dinheiro, precisa ser um UUID válido")
        @UUID(message = "Sender's account ID must be a valid UUID", allowNil = false)
        String accountSenderId
) {
}

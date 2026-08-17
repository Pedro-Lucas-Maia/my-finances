package pedromaia.dev.myfinances.bank.application.input;

import org.hibernate.validator.constraints.UUID;

public record GetBankByIdInput(
        @UUID(message = "Bank id must be a valid UUID")
        String id
) {
}

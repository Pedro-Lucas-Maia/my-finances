package pedromaia.dev.myfinances.transaction.application.input;

import org.hibernate.validator.constraints.UUID;

public record GetTransactionByIdInput (
        @UUID(message = "Transaction id must be a valid UUID")
        String uuid
){
}

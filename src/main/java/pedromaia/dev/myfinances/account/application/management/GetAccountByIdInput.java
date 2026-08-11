package pedromaia.dev.myfinances.account.application.management;

import org.hibernate.validator.constraints.UUID;

public record GetAccountByIdInput(
        @UUID(message = "Account ID must be a valid UUID")
        String accountId
) {
}

package pedromaia.dev.myfinances.account.application.management;

import org.hibernate.validator.constraints.UUID;

public record DeleteAccountInput(
        @UUID(message = "Account ID must be a valid UUID")
        String accountId
) {
}

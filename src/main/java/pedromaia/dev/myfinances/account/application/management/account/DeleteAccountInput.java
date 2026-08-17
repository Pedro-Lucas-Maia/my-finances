package pedromaia.dev.myfinances.account.application.management.account;

import org.hibernate.validator.constraints.UUID;

public record DeleteAccountInput(
        @UUID(message = "Account ID must be a valid UUID", allowNil = false)
        String accountId
) {
}

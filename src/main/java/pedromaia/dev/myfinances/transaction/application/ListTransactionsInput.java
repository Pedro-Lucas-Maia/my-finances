package pedromaia.dev.myfinances.transaction.application;

import org.hibernate.validator.constraints.UUID;

import java.time.LocalDate;

public record ListTransactionsInput(
        @UUID(message = "Transaction Category ID must be a valid UUID")
        String categoryId,
        String type,
        LocalDate referenceDate
) {
}

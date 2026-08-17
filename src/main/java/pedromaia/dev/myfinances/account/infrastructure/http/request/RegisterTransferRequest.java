package pedromaia.dev.myfinances.account.infrastructure.http.request;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.account.application.operations.RegisterTransferInput;

import java.math.BigDecimal;

public record RegisterTransferRequest(
        String description,
        BigDecimal amount,
        String categoryId,
        String accountReceiverId
) {
    public static RegisterTransferInput toInput(String accountSenderId, @NonNull RegisterTransferRequest request) {
        return new RegisterTransferInput(
                request.description,
                request.amount,
                request.categoryId,
                request.accountReceiverId,
                accountSenderId
        );
    }
}

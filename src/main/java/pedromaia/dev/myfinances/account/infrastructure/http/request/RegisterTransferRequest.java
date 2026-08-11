package pedromaia.dev.myfinances.account.infrastructure.http.request;

import pedromaia.dev.myfinances.account.application.operations.RegisterTransferInput;

import java.math.BigDecimal;

public record RegisterTransferRequest(
        String description,
        BigDecimal amount,
        String categoryId,
        String accountReceiverId
) {
    public static RegisterTransferInput toInput(String accountSenderId, RegisterTransferRequest request) {
        return new RegisterTransferInput(
                request.description,
                request.amount,
                request.categoryId,
                request.accountReceiverId,
                accountSenderId
        );
    }
}

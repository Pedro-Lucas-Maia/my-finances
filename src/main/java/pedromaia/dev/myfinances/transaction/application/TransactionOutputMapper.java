package pedromaia.dev.myfinances.transaction.application;

import org.springframework.stereotype.Component;
import pedromaia.dev.myfinances.transaction.application.category.GetTransactionCategoryByIdInput;
import pedromaia.dev.myfinances.transaction.application.category.GetTransactionCategoryByIdUseCase;
import pedromaia.dev.myfinances.transaction.application.ports.AccountQueryPort;
import pedromaia.dev.myfinances.transaction.domain.Transaction;

import java.util.Optional;

@Component
public class TransactionOutputMapper {

    private final GetTransactionCategoryByIdUseCase getTransactionCategoryByIdUseCase;
    private final AccountQueryPort accountQueryPort;

    public TransactionOutputMapper(GetTransactionCategoryByIdUseCase getTransactionCategoryByIdUseCase, AccountQueryPort accountQueryPort) {
        this.getTransactionCategoryByIdUseCase = getTransactionCategoryByIdUseCase;
        this.accountQueryPort = accountQueryPort;
    }

    public TransactionOutput toOutput(Transaction transaction) {
        var category = getTransactionCategoryByIdUseCase.execute(new GetTransactionCategoryByIdInput(transaction.getCategoryId().uuid().toString()));

        return TransactionOutput.from(
                transaction,
                category.name(),
                buildReceiver(transaction),
                buildSender(transaction)
        );
    }

    private TransactionOutput.AccountSender buildSender(Transaction transaction) {
        return Optional.ofNullable(transaction.getAccountSenderId())
                .map(id -> id.uuid().toString())
                .map(idStr -> new TransactionOutput.AccountSender(idStr, accountQueryPort.getAccountName(idStr)))
                .orElse(null);
    }

    private TransactionOutput.AccountReceiver buildReceiver(Transaction transaction) {
        return Optional.ofNullable(transaction.getAccountReceiverId())
                .map(id -> id.uuid().toString())
                .map(idStr -> new TransactionOutput.AccountReceiver(idStr, accountQueryPort.getAccountName(idStr)))
                .orElse(null);
    }
}

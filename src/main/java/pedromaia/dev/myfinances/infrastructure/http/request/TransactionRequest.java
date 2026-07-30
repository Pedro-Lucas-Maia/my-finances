package pedromaia.dev.myfinances.infrastructure.http.request;


import pedromaia.dev.myfinances.application.input.PersistTransactionInput;
import pedromaia.dev.myfinances.domain.Category;

public record TransactionRequest(String description, Category category, long amount) {
    public PersistTransactionInput toInput() {
        return new PersistTransactionInput(description, amount, category);
    }
}

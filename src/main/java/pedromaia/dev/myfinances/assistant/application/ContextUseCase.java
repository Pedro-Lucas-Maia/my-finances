package pedromaia.dev.myfinances.assistant.application;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pedromaia.dev.myfinances.account.application.management.account.ListAccountsUseCase;
import pedromaia.dev.myfinances.transaction.application.category.ListTransactionCategoriesUseCase;

import java.util.stream.Collectors;


@Service
public class ContextUseCase {
    private final ListAccountsUseCase listAccountsUseCase;
    private final ListTransactionCategoriesUseCase listTransactionCategoriesUseCase;

    public ContextUseCase(ListAccountsUseCase listAccountsUseCase, ListTransactionCategoriesUseCase listTransactionCategoriesUseCase) {
        this.listAccountsUseCase = listAccountsUseCase;
        this.listTransactionCategoriesUseCase = listTransactionCategoriesUseCase;
    }

    @Cacheable("accounts-context")
    public String getAccountsContext() {
        return listAccountsUseCase.execute()
                .stream()
                .map(account -> "'" + account.name() + "': '" + account.accountId() + "'")
                .collect(Collectors.joining(", ", "{", "}"));
    }

    @Cacheable("categories-context")
    public String getCategoriesContext() {
        return listTransactionCategoriesUseCase.execute()
                .stream()
                .map(category -> "'" + category.name() + "': '" + category.categoryId() + "'")
                .collect(Collectors.joining(", ", "{", "}"));
    }
}

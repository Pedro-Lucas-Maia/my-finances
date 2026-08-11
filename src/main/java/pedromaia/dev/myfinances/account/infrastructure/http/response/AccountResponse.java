package pedromaia.dev.myfinances.account.infrastructure.http.response;

import pedromaia.dev.myfinances.account.application.management.AccountOutput;

import java.math.BigDecimal;

public record AccountResponse(String accountId, String name, BigDecimal balance, Category category, Bank bank) {
    public static AccountResponse from(AccountOutput output) {
        return new AccountResponse(
                output.accountId(),
                output.name(),
                output.balance(),
                new Category(output.category().categoryId(), output.category().name()),
                new Bank(output.Bank().bankId(), output.Bank().name())
        );
    }
    public record Category(String categoryId, String name) {}
    public record Bank(String bankId, String name) {}
}

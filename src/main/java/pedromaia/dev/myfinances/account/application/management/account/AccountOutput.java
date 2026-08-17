package pedromaia.dev.myfinances.account.application.management.account;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.account.domain.Account;

import java.math.BigDecimal;

public record AccountOutput(
        String accountId,
        String name,
        BigDecimal balance,
        Category category,
        Bank Bank
) {
    public record Category(String categoryId, String name){}
    public record Bank(String bankId, String name) {}
    public static AccountOutput from(@NonNull Account account, String categoryName, String bankName) {
        return new AccountOutput(
                account.getId().uuid().toString(),
                account.getName(),
                account.getBalance(),
                new Category(account.getCategoryId().uuid().toString(), categoryName),
                new Bank(account.getBankId().uuid().toString(), bankName)
        );
    }
}

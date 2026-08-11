package pedromaia.dev.myfinances.account.application.management;

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
}

package pedromaia.dev.myfinances.bank.domain;

import java.util.UUID;

public record BankId(UUID uuid) {
    public BankId() {
        this(UUID.randomUUID());
    }
}

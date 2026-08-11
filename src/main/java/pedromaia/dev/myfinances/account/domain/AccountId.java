package pedromaia.dev.myfinances.account.domain;

import java.util.UUID;

public record AccountId(UUID uuid) {
    public AccountId() {
        this(UUID.randomUUID());
    }
}

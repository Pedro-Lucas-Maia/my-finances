package pedromaia.dev.myfinances.account.domain;

import java.util.UUID;

public record CategoryId(UUID uuid) {
    public CategoryId() {
        this(UUID.randomUUID());
    }
}

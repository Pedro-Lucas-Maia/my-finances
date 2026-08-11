package pedromaia.dev.myfinances.transaction.domain;

import java.util.UUID;

public record CategoryId(UUID uuid) {
    public CategoryId() {
        this(UUID.randomUUID());
    }
}

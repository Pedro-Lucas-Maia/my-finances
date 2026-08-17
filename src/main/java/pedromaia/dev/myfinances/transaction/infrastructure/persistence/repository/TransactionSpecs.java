package pedromaia.dev.myfinances.transaction.infrastructure.persistence.repository;

import org.springframework.data.jpa.domain.Specification;
import pedromaia.dev.myfinances.transaction.domain.Type;
import pedromaia.dev.myfinances.transaction.infrastructure.persistence.entity.TransactionEntity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

public class TransactionSpecs<T> {
    public static Specification<TransactionEntity> byCategory(String categoryId) {
        return (root, query, builder) ->
                categoryId == null ? null : builder.equal(root.get("categoryId"), UUID.fromString(categoryId));
    }

    public static Specification<TransactionEntity> byType(Type type) {
        return (root, query, builder) ->
                type == null ? null : builder.equal(root.get("type"), type);
    }

    public static Specification<TransactionEntity> byLast30days(LocalDate referenceDate) {

        return (root, query, builder) -> {
            if (referenceDate == null) return null;
            Timestamp thirtyDaysAgo = Timestamp.valueOf(referenceDate.minusDays(30).atStartOfDay());

            return builder.greaterThan(root.get("date"), thirtyDaysAgo);
        };
    }
}

package pedromaia.dev.myfinances.transaction.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pedromaia.dev.myfinances.transaction.domain.Category;
import pedromaia.dev.myfinances.transaction.domain.CategoryId;

import java.util.UUID;

@Entity
@Table(name = "transaction_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCategoryEntity {
    @Id
    private UUID id;
    private String name;

    public static TransactionCategoryEntity from(Category category) {
        return new TransactionCategoryEntity(
                category.getCategoryId().uuid(),
                category.getName()
        );
    }

    public static Category toDomain(TransactionCategoryEntity entity) {
        return new Category(
                new CategoryId(entity.getId()),
                entity.getName()
        );
    }
}

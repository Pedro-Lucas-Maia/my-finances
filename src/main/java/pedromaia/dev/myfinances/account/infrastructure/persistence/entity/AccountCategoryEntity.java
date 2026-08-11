package pedromaia.dev.myfinances.account.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pedromaia.dev.myfinances.account.domain.Category;
import pedromaia.dev.myfinances.account.domain.CategoryId;

import java.util.UUID;

@Entity
@Table(name = "account_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCategoryEntity {
    @Id
    private UUID id;

    private String name;

    public static AccountCategoryEntity from(Category category) {
        return new AccountCategoryEntity(
                category.getId().uuid(),
                category.getName()
        );
    }

    public static Category toDomain(AccountCategoryEntity category) {
        return new Category(
                new CategoryId(category.getId()),
                category.getName()
        );
    }
}

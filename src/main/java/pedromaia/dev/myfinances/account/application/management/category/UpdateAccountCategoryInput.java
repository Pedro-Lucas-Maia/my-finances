package pedromaia.dev.myfinances.account.application.management.category;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

public record UpdateAccountCategoryInput(
        @UUID(message = "Account Category id must be a valid UUID", allowNil = false)
        String id,

        @NotBlank(message = "new Account Category name can't be blank")
        @Length(min = 2, max = 30, message = "Account Category new name length must be between 2 and 30 characters")
        String name
) {
}

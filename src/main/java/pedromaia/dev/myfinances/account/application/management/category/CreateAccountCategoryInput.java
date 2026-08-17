package pedromaia.dev.myfinances.account.application.management.category;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateAccountCategoryInput(
        @NotBlank(message = "Account Category name can't be blank")
        @Length(min = 2, max = 30, message = "Account Category name must be between 2 and 30 characters")
        String name
) {
}

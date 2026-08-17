package pedromaia.dev.myfinances.account.infrastructure.http.request;

import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.account.application.management.category.CreateAccountCategoryInput;

public record CreateAccountCategoryRequest(
        String name
) {
    public static CreateAccountCategoryInput toInput(@NonNull CreateAccountCategoryRequest request) {
        return new CreateAccountCategoryInput(
                request.name
        );
    }
}

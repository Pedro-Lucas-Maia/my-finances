package pedromaia.dev.myfinances.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import pedromaia.dev.myfinances.transaction.domain.exception.CategoryNameNotValidException;

@Getter
@AllArgsConstructor
public class Category {
    private CategoryId categoryId;
    private String name;

    public Category(String name) {
        this.categoryId = new CategoryId();
        this.name = name;
    }
    public Category updateName(@NonNull String name) {
        if (name.isBlank()) {
            throw new CategoryNameNotValidException("Category name can't be blank");
        }
        if (name.equals(this.name)) {
            throw new CategoryNameNotValidException("Category new name can't be the same as the old one");
        }
        this.name = name;
        return this;
    }
}

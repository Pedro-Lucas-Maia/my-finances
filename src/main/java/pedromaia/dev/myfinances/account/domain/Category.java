package pedromaia.dev.myfinances.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pedromaia.dev.myfinances.account.domain.exception.CategoryNameNotValidException;

@Getter
@AllArgsConstructor
public class Category {
    private CategoryId id;
    private String name;

    public Category(String name) {
        this.id = new CategoryId();
        this.name = name;
    }
    public Category updateName(String newName) throws CategoryNameNotValidException {
        if (newName.isBlank()) {
            throw new CategoryNameNotValidException("Category name " + newName + " is not valid");
        }
        if (newName.equals(this.name)) {
            throw new CategoryNameNotValidException("Category new name can't be the same as the old one");
        }
        this.name = newName;
        return this;
    }
}

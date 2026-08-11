package pedromaia.dev.myfinances.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Category {
    private CategoryId categoryId;
    private String name;

    public Category(String name) {
        this.categoryId = new CategoryId();
        this.name = name;
    }
}

package pedromaia.dev.myfinances.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Category {
    private CategoryId id;
    private String name;

    public Category(String name) {
        this.id = new CategoryId();
        this.name = name;
    }
}

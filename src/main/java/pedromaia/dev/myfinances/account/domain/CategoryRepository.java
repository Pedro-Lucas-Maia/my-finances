package pedromaia.dev.myfinances.account.domain;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    List<Category> findAll();
    Optional<Category> findById(CategoryId id);
    void delete(Category category);
    boolean existsByName(String name);
}

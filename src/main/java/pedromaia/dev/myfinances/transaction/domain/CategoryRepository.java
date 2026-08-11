package pedromaia.dev.myfinances.transaction.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(UUID uuid);
    List<Category> findAll();
    void deleteById(UUID uuid);
}

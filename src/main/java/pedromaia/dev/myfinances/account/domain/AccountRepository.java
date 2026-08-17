package pedromaia.dev.myfinances.account.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(UUID id);
    List<Account> findAll();
    List<Account> findAllByCategoryId(CategoryId categoryId);
    void delete(Account account);
    boolean existsByName(String name);
}

package pedromaia.dev.myfinances.transaction.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(UUID uuid);
    List<Transaction> findAll();
    void deleteById(UUID uuid);
    List<Transaction> findAllByCategoryId(String categoryId);
}

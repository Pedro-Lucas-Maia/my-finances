package pedromaia.dev.myfinances.infrastructure.persistence.repository;


import org.springframework.stereotype.Repository;
import pedromaia.dev.myfinances.domain.Category;
import pedromaia.dev.myfinances.domain.Transaction;
import pedromaia.dev.myfinances.domain.TransactionRepository;
import pedromaia.dev.myfinances.infrastructure.persistence.entity.TransactionEntity;

import java.util.List;

@Repository
public class JpaTransactionRepository implements TransactionRepository {
    private final TransactionEntityRepository transactionEntityRepository;

    public JpaTransactionRepository(TransactionEntityRepository transactionEntityRepository) {
        this.transactionEntityRepository = transactionEntityRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        var entity = TransactionEntity.from(transaction);
        return transactionEntityRepository.save(entity).toDomain();
    }

    @Override
    public List<Transaction> findAllByCategory(Category category) {
        return transactionEntityRepository.findAllByCategory(category)
                .stream()
                .map(TransactionEntity::toDomain)
                .toList();
    }
}

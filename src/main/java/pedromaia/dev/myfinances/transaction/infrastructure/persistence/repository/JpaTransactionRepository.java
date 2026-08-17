package pedromaia.dev.myfinances.transaction.infrastructure.persistence.repository;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import pedromaia.dev.myfinances.transaction.domain.Transaction;
import pedromaia.dev.myfinances.transaction.domain.TransactionRepository;
import pedromaia.dev.myfinances.transaction.infrastructure.persistence.entity.TransactionEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Optional<Transaction> findById(UUID uuid) {
        return transactionEntityRepository.findById(uuid)
                .map(TransactionEntity::toDomain);
    }

    @Override
    public List<Transaction> findAll(Specification<TransactionEntity> spec) {
        return transactionEntityRepository.findAll(spec)
                .stream()
                .map(TransactionEntity::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID uuid) {
        transactionEntityRepository.deleteById(uuid);
    }

    @Override
    public List<Transaction> findAllByCategoryId(String categoryId) {
        return transactionEntityRepository.findAllByCategoryId(UUID.fromString(categoryId))
                .stream()
                .map(TransactionEntity::toDomain)
                .toList();
    }
}

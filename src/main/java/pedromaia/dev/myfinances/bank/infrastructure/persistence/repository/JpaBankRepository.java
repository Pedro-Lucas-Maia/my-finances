package pedromaia.dev.myfinances.bank.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import pedromaia.dev.myfinances.bank.domain.Bank;
import pedromaia.dev.myfinances.bank.domain.BankRepository;
import pedromaia.dev.myfinances.bank.infrastructure.persistence.entity.BankEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaBankRepository implements BankRepository {
    private final BankEntityRepository bankRepository;

    public JpaBankRepository(BankEntityRepository repository) {
        this.bankRepository = repository;
    }

    @Override
    public Bank save(Bank bank) {
        return BankEntity.toDomain(bankRepository.save(BankEntity.from(bank)));
    }

    @Override
    public Optional<Bank> findById(UUID uuid) {
        return bankRepository.findById(uuid)
                .map(BankEntity::toDomain);
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll()
                .stream()
                .map(BankEntity::toDomain)
                .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return bankRepository.existsByName(name);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return bankRepository.existsById(uuid);
    }
}

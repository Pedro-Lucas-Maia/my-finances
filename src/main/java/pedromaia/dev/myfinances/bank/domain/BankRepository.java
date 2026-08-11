package pedromaia.dev.myfinances.bank.domain;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankRepository {
    Bank save(Bank bank);
    Optional<Bank> findById(UUID uuid);
    List<Bank> findAll();
    boolean existsByName(String name);
    boolean existsById(UUID uuid);
}

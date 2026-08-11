package pedromaia.dev.myfinances.bank.application.output;

import pedromaia.dev.myfinances.bank.domain.Bank;

public record BankOutput(String id, String name) {
    public static BankOutput from(Bank bank) {
        return new BankOutput(bank.getId().uuid().toString(), bank.getName());
    }
}

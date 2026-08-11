package pedromaia.dev.myfinances.bank.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Bank {
    private BankId id;
    private String name;

    public Bank(String name) {
        this.id = new BankId();
        this.name = name;
    }
}

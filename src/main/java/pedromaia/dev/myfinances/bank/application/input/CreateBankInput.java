package pedromaia.dev.myfinances.bank.application.input;

import jakarta.validation.constraints.NotBlank;

public record CreateBankInput (@NotBlank(message = "Bank name cannot be blank") String name) {
}

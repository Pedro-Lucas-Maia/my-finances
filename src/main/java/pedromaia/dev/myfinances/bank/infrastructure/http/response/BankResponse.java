package pedromaia.dev.myfinances.bank.infrastructure.http.response;

import pedromaia.dev.myfinances.bank.application.output.BankOutput;

public record BankResponse(String id, String name) {
    public static BankResponse from(BankOutput bankOutput) {
        return new BankResponse(bankOutput.id(), bankOutput.name());
    }
}

package pedromaia.dev.myfinances.assistant.infrastructure.ai;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import pedromaia.dev.myfinances.account.application.operations.* ;

@Component
public class AssistantTools {

    private final RegisterExpenseUseCase registerExpenseUseCase;
    private final RegisterIncomeUseCase registerIncomeUseCase;
    private final RegisterTransferUseCase registerTransferUseCase;

    public AssistantTools(RegisterExpenseUseCase registerExpenseUseCase, RegisterIncomeUseCase registerIncomeUseCase, RegisterTransferUseCase registerTransferUseCase) {
        this.registerExpenseUseCase = registerExpenseUseCase;
        this.registerIncomeUseCase = registerIncomeUseCase;
        this.registerTransferUseCase = registerTransferUseCase;
    }

    @Tool(description = "Registra uma despesa/gasto financeiro na conta do usuário")
    public RegisterExpenseOutput registerExpenseTool(RegisterExpenseInput input) {
        return registerExpenseUseCase.execute(input);
    }

    @Tool(description = "Registra um ganho/receita financeira na conta do usuário")
    public RegisterIncomeOutput registerIncomeTool(RegisterIncomeInput input) {
        return registerIncomeUseCase.execute(input);
    }

    @Tool(description = "Registra uma transferência financeira entre contas do usuário")
    public RegisterTransferOutput registerTransferTool(RegisterTransferInput input) {
        return registerTransferUseCase.execute(input);
    }
}

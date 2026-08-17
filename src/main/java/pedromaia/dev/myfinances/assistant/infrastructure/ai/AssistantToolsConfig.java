package pedromaia.dev.myfinances.assistant.infrastructure.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import pedromaia.dev.myfinances.account.application.operations.*;

import java.util.function.Function;

@Configuration
public class AssistantToolsConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        String systemText = """
                Você é um assistente financeiro pessoal muito eficiente.    \s
                Sua tarefa é extrair a intenção do usuário (via áudio ou    \s
                texto) e invocar a ferramenta financeira correta (despesa, receita ou     \s
                transferência).                                                           \s
                                                                            \s
                Você DEVE usar estritamente os IDs abaixo para preencher os \s
                parâmetros das ferramentas:                                               \s
                Contas cadastradas: {accounts}                              \s
                Categorias cadastradas: {categories}                        \s
                                                                            \s
                Regras de execução:                                         \s
                1. Se o usuário disser onde gastou (ex: "padaria", "uber"), \s
                mas não disser a categoria, deduza a melhor categoria do nosso mapa.      \s
                2. Se o usuário não falar de qual conta o dinheiro saiu ou  \s
                entrou, assuma sempre que foi da 'Conta Corrente'.                        \s
                3. Responda o usuário de forma extremamente curta e amigável\s
                apenas confirmando o que foi feito (ex: "Pronto, registrei 50 reais na    \s
                padaria!").                                                               \s
               \s""";
        return builder
                .defaultSystem(s -> s.text(systemText))
                .build();
    }
}

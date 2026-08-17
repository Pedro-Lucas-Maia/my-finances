package pedromaia.dev.myfinances.assistant.application;

import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;
import pedromaia.dev.myfinances.assistant.domain.AIException;
import pedromaia.dev.myfinances.assistant.infrastructure.ai.AssistantTools;

import java.util.Objects;

@Service
public class ProcessAIRequestUseCase {
    private final ChatClient chatClient;
    private final ContextUseCase contextUseCase;
    private final TextToSpeechModel textToSpeechModel;
    private final pedromaia.dev.myfinances.assistant.infrastructure.ai.AssistantTools assistantTools;

    public ProcessAIRequestUseCase(ChatClient chatClient, ContextUseCase contextUseCase, TextToSpeechModel textToSpeechModel, pedromaia.dev.myfinances.assistant.infrastructure.ai.AssistantTools assistantTools) {
        this.chatClient = chatClient;
        this.contextUseCase = contextUseCase;
        this.textToSpeechModel = textToSpeechModel;
        this.assistantTools = assistantTools;
    }

    public ByteArrayResource execute(MultipartFile file) {
        var result = chatClient.prompt()
                .system(s ->
                        s.param("accounts", contextUseCase.getAccountsContext())
                                .param("categories", contextUseCase.getCategoriesContext()))
                .user(u ->
                        u.text("Ouça o áudio e execute a transação correspondente.")
                                .media(MimeType.valueOf(Objects.requireNonNull(file.getContentType())), file.getResource()))
                .tools(assistantTools)
                .call()
                .content();

        if (result == null) {
            throw new AIException("Our AI couldn't process your request, please try again");
        }
        byte[] audioBytes = textToSpeechModel.call(result);
        return new ByteArrayResource(audioBytes);
    }
}


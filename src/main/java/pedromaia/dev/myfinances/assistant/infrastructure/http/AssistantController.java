package pedromaia.dev.myfinances.assistant.infrastructure.http;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pedromaia.dev.myfinances.assistant.application.ProcessAIRequestUseCase;

@RestController
@RequestMapping("/ai")
public class AssistantController {
    private final ProcessAIRequestUseCase processAIRequestUseCase;

    public AssistantController(ProcessAIRequestUseCase processAIRequestUseCase) {
        this.processAIRequestUseCase = processAIRequestUseCase;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "audio/mpeg")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ByteArrayResource> registerWithAI(@RequestParam("file")MultipartFile file) {
        var audioBytes = processAIRequestUseCase.execute(file);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(audioBytes);
    }
}

package pedromaia.dev.myfinances.assistant.domain;

public class AIException extends RuntimeException {
    public AIException(String message) {
        super(message);
    }
}

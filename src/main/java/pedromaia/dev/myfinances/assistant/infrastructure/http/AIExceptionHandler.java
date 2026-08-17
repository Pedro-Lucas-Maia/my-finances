package pedromaia.dev.myfinances.assistant.infrastructure.http;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pedromaia.dev.myfinances.assistant.domain.AIException;
import pedromaia.dev.myfinances.common.infrastructure.http.ErrorResponse;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = "pedromaia.dev.myfinances.assistant")
public class AIExceptionHandler {
    @ExceptionHandler(AIException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAIException(AIException e, HttpServletRequest request) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("AI error")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}

package pedromaia.dev.myfinances.transaction.infrastructure.http;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pedromaia.dev.myfinances.common.infrastructure.http.ErrorResponse;
import pedromaia.dev.myfinances.transaction.domain.exception.CategoryNotDeletableException;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = "pedromaia.dev.myfinances.transaction")
public class TransactionExceptionHandler {
    @ExceptionHandler(CategoryNotDeletableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCategoryNotDeletableException(@NonNull CategoryNotDeletableException e, @NonNull HttpServletRequest request) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Category in use can't be deleted")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}

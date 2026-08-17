package pedromaia.dev.myfinances.account.infrastructure.http;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pedromaia.dev.myfinances.account.domain.exception.CategoryNotDeletableException;
import pedromaia.dev.myfinances.common.infrastructure.http.ErrorResponse;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = "pedromaia.dev.myfinances.account")
public class AccountExceptionHandler {
    @ExceptionHandler(CategoryNotDeletableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCategoryNotDeletableException(CategoryNotDeletableException e, HttpServletRequest request) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Category in use can't be deleted")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}

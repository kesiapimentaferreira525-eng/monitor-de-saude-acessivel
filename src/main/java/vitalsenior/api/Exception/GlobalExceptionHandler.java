package vitalsenior.api.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {
        return createResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        int globalErrorIndex = 0;
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            int currentIndex = globalErrorIndex++;
            String key = currentIndex == 0 ? "global" : "global." + currentIndex;
            errors.put(key, error.getDefaultMessage());
        }

        return createResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Erro de validação", errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRequestBody(HttpMessageNotReadableException ex) {
        return createResponse(HttpStatus.BAD_REQUEST, "Corpo da requisição inválido", null);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
        return createResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Tipo de conteúdo não suportado", null);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthenticationException(AuthenticationException ex) {
        return createResponse(HttpStatus.UNAUTHORIZED, "Credenciais inválidas", null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException ex) {
        return createResponse(HttpStatus.FORBIDDEN, "Acesso negado", null);
    }

    private ResponseEntity<Map<String, Object>> createResponse(
            HttpStatus status,
            String message,
            Map<String, String> fieldErrors
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);
        if (fieldErrors != null) {
            response.put("messages", fieldErrors);
        }
        return ResponseEntity.status(status).body(response);
    }
}
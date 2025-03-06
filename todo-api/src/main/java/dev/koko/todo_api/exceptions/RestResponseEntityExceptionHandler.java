package dev.koko.todo_api.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { HttpException.class })
    protected ResponseEntity<Object> handleHttpException(HttpException ex, WebRequest request) {
        final HttpResponse httpResponse = new HttpResponse(Arrays.asList(ex.getMessage()), ex.getHttpStatus());
        return this.handleExceptionInternal(ex, httpResponse, new HttpHeaders(), ex.getHttpStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        final HttpResponse httpResponse = _buildErrorResponse(ex.getBindingResult());
        return this.handleExceptionInternal(ex, httpResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    private HttpResponse _buildErrorResponse(BindingResult bindingResult) {
        final List<String> errorMessages = bindingResult.getFieldErrors()
            .stream()
            .map(err -> err.getField().concat(" : ").concat(err.getDefaultMessage()))
            .toList();

        return new HttpResponse(errorMessages, HttpStatus.BAD_REQUEST);
    }
}

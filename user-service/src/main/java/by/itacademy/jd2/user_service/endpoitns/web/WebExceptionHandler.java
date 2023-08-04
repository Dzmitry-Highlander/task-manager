package by.itacademy.jd2.user_service.endpoitns.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {
    //TODO handle all exceptions
    @ExceptionHandler(value = {IllegalStateException.class})
    protected ResponseEntity<Object> handleAuthenticationConflict(
            RuntimeException exception, WebRequest request
    ) {
        String bodyOfResponse = exception.getMessage();

        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }
}

package by.itacademy.jd2.task_service.endpoint.advice;

import by.itacademy.jd2.base_package.core.dto.StructuredErrorResponseDTO;
import by.itacademy.jd2.base_package.core.enums.EErrorType;
import by.itacademy.jd2.base_package.core.dto.ErrorResponseDTO;
import by.itacademy.jd2.task_service.service.exception.ItemNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String ITEM_NOT_FOUND_ERROR =
            "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз";
    private static final String INTERNAL_SERVER_ERROR =
            "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору";
    private static final String INVALID_ARGUMENT_ERROR = "Запрос содержит некорректные символы";

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuditNotFoundError(ItemNotFoundException exception) {
        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .logref(EErrorType.ERROR)
                .message(ITEM_NOT_FOUND_ERROR)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleInvalidArgument(ConstraintViolationException exception) {
        StructuredErrorResponseDTO response =
                new StructuredErrorResponseDTO(EErrorType.STRUCTURED_ERROR, new HashMap<>());
        Map<String, String> errors = response.getErrors();

        exception.getConstraintViolations()
                .forEach(v -> errors.put(v.getPropertyPath().toString(), INVALID_ARGUMENT_ERROR));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            IOException.class,
            IndexOutOfBoundsException.class,
            ArithmeticException.class,
            Error.class
    })
    public ResponseEntity<ErrorResponseDTO> handleInnerError(){
        ErrorResponseDTO response = new ErrorResponseDTO(EErrorType.ERROR, INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

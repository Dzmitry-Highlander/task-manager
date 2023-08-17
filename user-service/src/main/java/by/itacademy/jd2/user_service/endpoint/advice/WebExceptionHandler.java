package by.itacademy.jd2.user_service.endpoint.advice;

import by.itacademy.jd2.user_service.core.dto.ErrorResponseDTO;
import by.itacademy.jd2.user_service.core.dto.StructuredErrorResponseDTO;
import by.itacademy.jd2.user_service.core.enums.EErrorType;
import by.itacademy.jd2.user_service.service.exception.EmailAlreadyTakenException;
import by.itacademy.jd2.user_service.service.exception.ItemNotFoundException;
import by.itacademy.jd2.user_service.service.exception.VersionsNotMatchException;
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
    private static final String DATA_NOT_CORRECT_ERROR =
            "Запрос содержит некорректные данные. Измените запрос и отправьте его еще раз";
    private static final String INTERNAL_SERVER_ERROR =
            "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору";
    private static final String INVALID_ARGUMENT_ERROR = "Запрос содержит некорректные символы";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StructuredErrorResponseDTO> handleInvalidArgument(ConstraintViolationException exception){
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

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleItemNotFoundError(ItemNotFoundException exception){
        ErrorResponseDTO response = new ErrorResponseDTO(EErrorType.ERROR, DATA_NOT_CORRECT_ERROR);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<StructuredErrorResponseDTO> handleEmailTakenError(EmailAlreadyTakenException exception){
        StructuredErrorResponseDTO response = new StructuredErrorResponseDTO(
                EErrorType.STRUCTURED_ERROR, new HashMap<>()
        );

        response.getErrors().put("email", INVALID_ARGUMENT_ERROR);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VersionsNotMatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleVersionsMathError(VersionsNotMatchException exception){
        ErrorResponseDTO response = new ErrorResponseDTO(EErrorType.ERROR, DATA_NOT_CORRECT_ERROR);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponseDTO> handleNumberFormatError(NumberFormatException exception){
        ErrorResponseDTO response = new ErrorResponseDTO(EErrorType.ERROR, DATA_NOT_CORRECT_ERROR);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //TODO при вводе некорректных данных Version не отлавливает Exception, handleVersionsMathError()
}

package by.itacademy.jd2.user_service.endpoitns.advice;

import by.itacademy.jd2.user_service.core.dto.ErrorResponseDTO;
import by.itacademy.jd2.user_service.core.dto.StructuredErrorResponseDTO;
import by.itacademy.jd2.user_service.core.enums.EErrorType;
import by.itacademy.jd2.user_service.service.exception.EmailAlreadyTakenException;
import by.itacademy.jd2.user_service.service.exception.ItemNotFoundException;
import by.itacademy.jd2.user_service.service.exception.VersionsNotMatchException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    private static final String INTERNAL_SERVER_ERROR = "Внутренняя ошибка сервера. Попробуйте позже";
    private static final String ITEM_NOT_FOUND_ERROR = "Невозможно найти пользователя с таким ";
    private static final String EMAIL_ALREADY_EXISTS_ERROR = "Пользователь с такой почтой уже зарегистрирован: ";
    private static final String VERSIONS_NOT_MATCH_ERROR = "Текущая версия не совпадает с указанной";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StructuredErrorResponseDTO> handleInvalidArgument(ConstraintViolationException exception){
        StructuredErrorResponseDTO response =
                new StructuredErrorResponseDTO(EErrorType.STRUCTURED_ERROR, new HashMap<>());
        Map<String, String> errors = response.getErrors();

        exception.getConstraintViolations()
                .forEach(v -> errors.put(v.getPropertyPath().toString(), v.getMessage()));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequest(){
        ErrorResponseDTO response = new ErrorResponseDTO(EErrorType.ERROR, DATA_NOT_CORRECT_ERROR);

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
    public ResponseEntity<ErrorResponseDTO> handleItemNotFountError(ItemNotFoundException exception){
        ErrorResponseDTO response = new ErrorResponseDTO(
                EErrorType.ERROR, ITEM_NOT_FOUND_ERROR + exception.getItem()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<StructuredErrorResponseDTO> handleEmailTakenError(EmailAlreadyTakenException exception){
        StructuredErrorResponseDTO response = new StructuredErrorResponseDTO(
                EErrorType.STRUCTURED_ERROR, new HashMap<>()
        );

        response.getErrors().put("email", EMAIL_ALREADY_EXISTS_ERROR + exception.getEmail());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VersionsNotMatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleVersionsMathError(VersionsNotMatchException exception){
        ErrorResponseDTO response = new ErrorResponseDTO(EErrorType.ERROR, VERSIONS_NOT_MATCH_ERROR);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponseDTO> handleNumberFormatError(NumberFormatException exception){
        ErrorResponseDTO response = new ErrorResponseDTO(EErrorType.ERROR, INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

package by.itacademy.jd.task_service.endpoint.advice;

import by.itacademy.jd.base_pakage.core.dto.ErrorResponseDTO;
import by.itacademy.jd.base_pakage.core.enums.EErrorType;
import by.itacademy.jd.task_service.service.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String ITEM_NOT_FOUND_ERROR =
            "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз";

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuditNotFoundError(ItemNotFoundException exception) {
        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .logref(EErrorType.ERROR)
                .message(ITEM_NOT_FOUND_ERROR)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

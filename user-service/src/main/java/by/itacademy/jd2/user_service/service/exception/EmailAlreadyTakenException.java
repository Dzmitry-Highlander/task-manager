package by.itacademy.jd2.user_service.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmailAlreadyTakenException extends RuntimeException {
    private String email;
}

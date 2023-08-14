package by.itacademy.jd2.user_service.service.api;

import by.itacademy.jd2.user_service.core.dto.UserUpdateDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ICRUDService<T, S> {
    T create(S item);

    List<T> read();

    T read(UUID id);

    T update(UUID id, LocalDateTime version, UserUpdateDTO item);
}

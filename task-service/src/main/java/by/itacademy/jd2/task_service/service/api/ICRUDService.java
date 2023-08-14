package by.itacademy.jd2.task_service.service.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ICRUDService<T, S, M> {
    T create(S item);

    List<T> read();

    T read(UUID uuid);

    T update(UUID uuid, LocalDateTime version, M item);
}

package by.itacademy.jd.task_service.service.api;

import java.util.List;
import java.util.UUID;

public interface ICRUDService<T, S> {
    T create(S item);

    List<T> read();

    T read(UUID uuid);

    T update(UUID uuid, Long version, S item);
}

package by.itacademy.jd2.user_service.service.api;

import java.util.List;
import java.util.UUID;

public interface ICRUDService<T, S> {
    T create(S item);

    List<T> read();

    T read(UUID id);

    T update(UUID id, Long version, S item);

    void delete(UUID id, Long version);
}

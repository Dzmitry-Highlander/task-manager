package by.itacademy.jd2.comments_service.service.api;

import java.util.List;
import java.util.UUID;

public interface ICRUDService<T, S> {
    T create(S item);

    T read(UUID id);

    List<T> read();

    void delete(UUID id);
}

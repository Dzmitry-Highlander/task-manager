package by.itacademy.jd2.user_service.dao.api;

import by.itacademy.jd2.user_service.dao.entity.Activator;
import by.itacademy.jd2.user_service.dao.entity.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface IActivatorRepository extends ListCrudRepository<Activator, UUID> {
    Optional<Activator> findByEmail(String email);
}

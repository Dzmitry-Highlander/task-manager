package by.itacademy.jd2.user_service.service.api;

import by.itacademy.jd2.user_service.core.dto.ActivatorDTO;

public interface IActivatorGenerator {
    ActivatorDTO generate(String email);
}

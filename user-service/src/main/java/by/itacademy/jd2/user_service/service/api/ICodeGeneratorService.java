package by.itacademy.jd2.user_service.service.api;

import by.itacademy.jd2.user_service.core.dto.ActivatorDTO;
import by.itacademy.jd2.user_service.core.dto.UserDTO;

public interface ICodeGeneratorService {
    ActivatorDTO generate(UserDTO userDTO);
}

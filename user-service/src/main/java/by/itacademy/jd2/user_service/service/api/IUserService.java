package by.itacademy.jd2.user_service.service.api;

import by.itacademy.jd2.user_service.core.dto.UserCreateDTO;
import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.dao.entity.User;

public interface IUserService extends ICRUDService<User, UserCreateDTO> {
    User findByEmail(String email);

    void activate(UserCreateDTO userCreateDTO);
}

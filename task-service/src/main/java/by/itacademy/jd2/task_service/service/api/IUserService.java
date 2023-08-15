package by.itacademy.jd2.task_service.service.api;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;

public interface IUserService {
    UserShortDTO getMe(String jwt);
}

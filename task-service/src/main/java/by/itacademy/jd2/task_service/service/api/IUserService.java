package by.itacademy.jd2.task_service.service.api;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.task_service.core.dto.UserRefDTO;

import java.util.List;

public interface IUserService {
    UserShortDTO getMe(String jwt);

    UserShortDTO get(UserRefDTO user, String jwt);

    List<UserShortDTO> get(List<UserRefDTO> staff, String jwt);

}

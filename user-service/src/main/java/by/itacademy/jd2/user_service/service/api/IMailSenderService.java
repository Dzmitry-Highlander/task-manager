package by.itacademy.jd2.user_service.service.api;

import by.itacademy.jd2.user_service.core.dto.UserDTO;

public interface IMailSenderService {
    void send(UserDTO userDTO);

    String buildEmail(UserDTO userDTO);
}

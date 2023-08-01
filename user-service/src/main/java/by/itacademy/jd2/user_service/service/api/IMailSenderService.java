package by.itacademy.jd2.user_service.service.api;

import by.itacademy.jd2.user_service.dao.entity.User;

public interface IMailSenderService {
    void send(String email);
}

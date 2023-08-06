package by.itacademy.jd2.user_service.service.api;

public interface IMailSenderService {
    void send(String code, String email);

    String buildEmail(String code);
}

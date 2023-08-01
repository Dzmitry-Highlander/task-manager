package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.config.properties.MailProperty;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IMailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService implements IMailSenderService {
    private final MailProperty property;
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        int code = 1;

        mailMessage.setFrom(property.getEmail());
        mailMessage.setTo(email);
        mailMessage.setText("Your code: " + code);

        mailSender.send(mailMessage);
    }
}

package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.config.properties.MailProperty;
import by.itacademy.jd2.user_service.service.api.IMailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService implements IMailSenderService {
    private final JavaMailSender mailSender;
    private final MailProperty property;

    @Override
    @Async
    public void send(String email) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText("TEST");
            helper.setTo(email);
            helper.setSubject("Confirm your email");
            helper.setFrom(property.getMail());

            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }
}

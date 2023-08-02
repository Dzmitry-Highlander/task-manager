package by.itacademy.jd2.user_service.endpoitns.web;

import by.itacademy.jd2.user_service.service.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class DemoController {
    private final MailSenderService mailSenderService;

    @GetMapping("/demo")
    public void demo() {
        mailSenderService.send("strixmymail@gmail.com");
    }
}

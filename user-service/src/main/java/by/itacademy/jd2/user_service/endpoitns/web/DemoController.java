package by.itacademy.jd2.user_service.endpoitns.web;

import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.service.MailSenderService;
import by.itacademy.jd2.user_service.service.api.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {
    private final ConversionService conversionService;
    private final MailSenderService mailSenderService;
    private final IUserService userService;

    @GetMapping
    public String demo(@RequestParam("code") UUID id) {
        mailSenderService.send(conversionService.convert(userService.read(id), UserDTO.class));

        return "Mail sent";
    }
}

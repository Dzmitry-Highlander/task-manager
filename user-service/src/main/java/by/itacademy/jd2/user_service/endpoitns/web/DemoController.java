package by.itacademy.jd2.user_service.endpoitns.web;

import by.itacademy.jd2.user_service.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class DemoController {
    private final AuthenticationService authenticationService;

    @GetMapping("/demo")
    public void demo() {
        authenticationService.verification("test@gmail.com");
    }
}

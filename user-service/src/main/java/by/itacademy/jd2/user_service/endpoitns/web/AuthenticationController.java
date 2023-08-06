package by.itacademy.jd2.user_service.endpoitns.web;

import by.itacademy.jd2.user_service.core.dto.UserLoginDTO;
import by.itacademy.jd2.user_service.core.dto.UserRegistrationDTO;
import by.itacademy.jd2.user_service.service.api.IAuthenticationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<?> register(
            @RequestBody @Valid UserRegistrationDTO request
    ) {
        authenticationService.register(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> register(
            @RequestBody @Valid UserLoginDTO request
    ) {
        var response = authenticationService.login(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/registration/confirm")
    public ResponseEntity<?> confirm(
            @RequestParam("code") String code,
            @RequestParam("email") @Email String email
    ) {
        var response = authenticationService.verification(code, email);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        var response = authenticationService.me();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

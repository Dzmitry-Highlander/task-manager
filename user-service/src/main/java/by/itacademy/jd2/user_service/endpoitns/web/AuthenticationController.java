package by.itacademy.jd2.user_service.endpoitns.web;

import by.itacademy.jd2.user_service.core.dto.UserLoginDTO;
import by.itacademy.jd2.user_service.core.dto.UserRegistrationDTO;
import by.itacademy.jd2.user_service.core.dto.AuthenticationResponseDTO;
import by.itacademy.jd2.user_service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody UserRegistrationDTO request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody UserLoginDTO request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/registration/confirm")
    public ResponseEntity<?> confirm(@RequestParam("code") Integer code, @RequestParam("email") String email) {
        return ResponseEntity.ok(this.authenticationService.confirm(code, email));
    }
}

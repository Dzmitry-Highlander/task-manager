package by.itacademy.jd2.user_service.endpoitns.web;

import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.core.dto.UserLoginDTO;
import by.itacademy.jd2.user_service.core.dto.UserRegistrationDTO;
import by.itacademy.jd2.user_service.core.dto.AuthenticationResponseDTO;
import by.itacademy.jd2.user_service.service.api.IAuthenticationService;
import by.itacademy.jd2.user_service.service.api.IUserHolder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService authenticationService;
    private final IUserHolder userHolder;

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody @Valid UserRegistrationDTO request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody @Valid UserLoginDTO request
    ) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/registration/confirm")
    public ResponseEntity<?> confirm(
            @RequestParam("code") String code,
            @RequestParam("email") @Email String email
    ) {
        return ResponseEntity.ok(this.authenticationService.verification(code, email));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me() {
        UserDetails userDetails = userHolder.getUser();

        return ResponseEntity.ok(this.authenticationService.me(userDetails.getUsername()));
    }
}

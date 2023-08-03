package by.itacademy.jd2.user_service.endpoitns.web;

import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.core.dto.UserLoginDTO;
import by.itacademy.jd2.user_service.core.dto.UserRegistrationDTO;
import by.itacademy.jd2.user_service.core.dto.AuthenticationResponseDTO;
import by.itacademy.jd2.user_service.dao.api.IUserRepository;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IAuthenticationService;
import by.itacademy.jd2.user_service.service.api.IUserHolder;
import by.itacademy.jd2.user_service.service.exception.AuthException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class AuthenticationController {
    private final ConversionService conversionService;
    private final IUserRepository userRepository;
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
        return ResponseEntity.ok(authenticationService.verification(code, email));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        Optional<User> userOptional = userRepository
                .findByEmail(userHolder.getUser().getUsername());
        UserDTO userDTO =  conversionService.convert(
                userOptional.orElseThrow(
                        () -> new AuthException("Access denied!")), UserDTO.class
        );

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}

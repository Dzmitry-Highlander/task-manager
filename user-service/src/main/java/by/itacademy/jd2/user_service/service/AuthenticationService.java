package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.dto.*;
import by.itacademy.jd2.user_service.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import by.itacademy.jd2.user_service.dao.api.IActivatorRepository;
import by.itacademy.jd2.user_service.dao.api.IUserRepository;
import by.itacademy.jd2.user_service.dao.entity.Activator;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IAuthenticationService;
import by.itacademy.jd2.user_service.service.api.IMailSenderService;
import by.itacademy.jd2.user_service.service.exception.AuthException;
import by.itacademy.jd2.user_service.service.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConversionService conversionService;
    private final IUserRepository userRepository;
    private final IActivatorRepository activatorRepository;
    private final IMailSenderService mailSenderService;

    @Override
    public AuthenticationResponseDTO register(UserRegistrationDTO request) {
        var user = User.builder()
                .fio(request.getFio())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(EUserRole.ROLE_USER)
                .status(EUserStatus.WAITING_ACTIVATION)
                .build();

        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
            userRepository.save(user);

            var code = CodeGenerator.generate();

            ActivatorCreateDTO item = ActivatorCreateDTO.builder()
                    .email(request.getEmail())
                    .code(code)
                    .createDate(System.currentTimeMillis())
                    .expirationDate(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30))
                    .build();

            mailSenderService.send(code, request.getEmail());
            activatorRepository.save(Objects.requireNonNull(conversionService.convert(item, Activator.class)));
        } else {
            throw new AuthException("Such user exists!");
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponseDTO login(UserLoginDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException ex) {
            throw new AuthException("Email or password is incorrect!");
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException("No such email registered!"));

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    @Transactional
    public String verification(String code, String mail) {
        Optional<User> userOptional = userRepository.findByEmail(mail);
        Optional<Activator> activatorOptional = activatorRepository.findByEmail(mail);
        User user = userOptional
                .orElseThrow(() -> new AuthException("Verification Error!"));
        Activator activator = activatorOptional
                .orElseThrow(() -> new AuthException("Verification Error!"));

        if (Objects.equals(code, activator.getCode())) {
            user.setStatus(EUserStatus.ACTIVATED);

            userRepository.save(user);

            return "Verification complete";
        }

        return "Verification failed";
    }

    @Override
    public UserDTO me(String email) {
        return conversionService.convert(userRepository.findByEmail(email), UserDTO.class);
    }
}

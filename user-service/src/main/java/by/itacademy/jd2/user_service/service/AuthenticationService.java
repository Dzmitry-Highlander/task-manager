package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.dto.*;
import by.itacademy.jd2.user_service.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import by.itacademy.jd2.user_service.dao.api.IActivatorRepository;
import by.itacademy.jd2.user_service.dao.entity.Activator;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private static final String EMAIL_OR_PASSWORD_ERROR = "Вы ввели неверный email или пароль";
    private static final String VERIFICATION_SUCCESS = "Пользователь верифицирован";
    private static final String VERIFICATION_FAILED = "Сервер не смог корректно обработать запрос. Пожалуйста " +
            "обратитесь к администратору";

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConversionService conversionService;
    private final IUserService userService;
    private final IActivatorRepository activatorRepository;
    private final IMailSenderService mailSenderService;
    private final IUserHolder userHolder;
    private final ICodeGeneratorService codeGeneratorService;

    @Override
    @Transactional
    public void register(UserRegistrationDTO request)   {
        var time = System.currentTimeMillis();
        var code = codeGeneratorService.generate();
        var user = UserCreateDTO.builder()
                .fio(request.getFio())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(EUserRole.ROLE_USER)
                .status(EUserStatus.WAITING_ACTIVATION)
                .build();
        var activator = Activator.builder()
                    .email(request.getEmail())
                    .code(code)
                    .createDate(
                            LocalDateTime.ofInstant(Instant.ofEpochMilli(time),
                                    TimeZone.getDefault().toZoneId())
                    )
                    .expirationDate(
                            LocalDateTime.ofInstant(Instant.ofEpochMilli(time + TimeUnit.MINUTES.toMillis(30)),
                                    TimeZone.getDefault().toZoneId())
                    )
                    .build();

        activatorRepository.save(Objects.requireNonNull(activator));
        mailSenderService.send(code, request.getEmail());
        userService.create(user);

        var jwtToken = jwtService.generateToken(conversionService.convert(user, User.class));

        AuthenticationResponseDTO.builder()
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
            throw new IllegalStateException(EMAIL_OR_PASSWORD_ERROR);
        }

        var user = userService.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(conversionService.convert(user, User.class));

        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    @Transactional
    public String verification(String code, String email) {
        Activator activator = activatorRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException(VERIFICATION_FAILED));
        User user = userService.findByEmail(email);

        if (Objects.equals(code, activator.getCode())) {
            UserCreateDTO userUpdate = UserCreateDTO.builder()
                    .role(user.getRole())
                    .build();

            //TODO отдельный метод на активацию пользователя
            userService.activate(userUpdate);

            return VERIFICATION_SUCCESS;
        } else {
            throw new IllegalStateException(VERIFICATION_FAILED);
        }
    }

    @Override
    public UserDTO me() {
        return conversionService.convert(
                userService.findByEmail(userHolder.getUser().getUsername()), UserDTO.class);
    }
}

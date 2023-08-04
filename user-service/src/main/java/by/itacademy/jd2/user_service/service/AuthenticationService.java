package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.dto.*;
import by.itacademy.jd2.user_service.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import by.itacademy.jd2.user_service.dao.api.IActivatorRepository;
import by.itacademy.jd2.user_service.dao.entity.Activator;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IAuthenticationService;
import by.itacademy.jd2.user_service.service.api.IMailSenderService;
import by.itacademy.jd2.user_service.service.api.IUserService;
import by.itacademy.jd2.user_service.service.exception.AuthException;
import by.itacademy.jd2.user_service.service.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class
AuthenticationService implements IAuthenticationService {
    private static final String USER_EXISTS_ERROR = "Пользователь с таким email уже зарегистрирован";
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

    @Override
    public AuthenticationResponseDTO register(UserRegistrationDTO request) {
        var user = UserCreateDTO.builder()
                .fio(request.getFio())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(EUserRole.ROLE_USER)
                .status(EUserStatus.WAITING_ACTIVATION)
                .build();

        if (Objects.nonNull(userService.findByEmail(request.getEmail()))) {
            var code = CodeGenerator.generate();
            ActivatorCreateDTO activator = ActivatorCreateDTO.builder()
                    .email(request.getEmail())
                    .code(code)
                    .createDate(System.currentTimeMillis())
                    .expirationDate(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30))
                    .build();

            userService.create(user);
            activatorRepository.save(Objects.requireNonNull(conversionService.convert(activator, Activator.class)));
            mailSenderService.send(code, request.getEmail());
        } else {
            throw new AuthException(USER_EXISTS_ERROR);
        }

        var jwtToken = jwtService.generateToken(conversionService.convert(user, User.class));

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
            throw new AuthException(EMAIL_OR_PASSWORD_ERROR);
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
                .orElseThrow(() -> new AuthException(VERIFICATION_FAILED));
        UserDTO user = userService.findByEmail(email);

        if (Objects.equals(code, activator.getCode())) {
            UserCreateDTO userUpdate = UserCreateDTO.builder()
                    .role(user.getRole())
                    .build();

            userService.update(user.getId(), user.getUpdateDate(), userUpdate);

            return VERIFICATION_SUCCESS;
        }

        return VERIFICATION_FAILED;
    }

    @Override
    public UserDTO me(String email) {
        return userService.findByEmail(email);
    }
}

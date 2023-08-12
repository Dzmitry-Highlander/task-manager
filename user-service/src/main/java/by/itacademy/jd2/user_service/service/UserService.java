package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.dto.UserCreateDTO;
import by.itacademy.jd2.user_service.core.dto.UserUpdateDTO;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import by.itacademy.jd2.user_service.dao.api.IUserRepository;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IAuditService;
import by.itacademy.jd2.user_service.service.api.IUserService;
import by.itacademy.jd2.user_service.service.exception.EmailAlreadyTakenException;
import by.itacademy.jd2.user_service.service.exception.ItemNotFoundException;
import by.itacademy.jd2.user_service.service.exception.VersionsNotMatchException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class UserService implements IUserService {
    private static final String USER_EXISTS_ERROR = "Пользователь с таким email уже зарегистрирован";
    private static final String USER_NOT_FOUND_ERROR = "Пользователь с таким email не найден";
    private static final String VERSIONS_NOT_MATCH_ERROR = "Версии не совпадают";
    private static final String USER_CREATION_REQUEST = "Запрос на создание пользователя";
    private static final String ALL_DATA_REQUEST = "Запрошены все данные по пользователям";
    private static final String UUID_DATA_REQUEST = "Запрошены данные по UUID пользователя";
    private static final String UPDATE_DATA_REQUEST = "Запрос на обновление данных пользователя";

    private final IUserRepository userRepository;
    private final IAuditService auditService;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public User create(@Valid UserCreateDTO item) {
        userRepository.findByEmail(item.getEmail())
                .ifPresent(e -> {
                    throw new EmailAlreadyTakenException(USER_EXISTS_ERROR);
                });

        User user = userRepository.save(
                Objects.requireNonNull(conversionService.convert(item, User.class)));

        auditService.send(USER_CREATION_REQUEST, user.getUuid().toString());

        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> read() {
        auditService.send(ALL_DATA_REQUEST, "all");

        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User read(UUID uuid) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException(USER_NOT_FOUND_ERROR));

        auditService.send(UUID_DATA_REQUEST, uuid.toString());

        return user;
    }

    @Transactional
    @Override
    public User update(UUID uuid, LocalDateTime version, @Valid UserUpdateDTO item) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException(USER_NOT_FOUND_ERROR));

        if (!version.equals(user.getUpdateDate())) {
            throw new VersionsNotMatchException(VERSIONS_NOT_MATCH_ERROR);
        }

        user.setFio(item.getFio());
        user.setRole(item.getRole());
        user.setStatus(item.getStatus());

        auditService.send(UPDATE_DATA_REQUEST, uuid.toString());

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ItemNotFoundException(USER_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public void activate(@Valid UserCreateDTO userCreateDTO) {
        User user = userRepository.findByEmail(userCreateDTO.getEmail())
                .orElseThrow(() -> new ItemNotFoundException(USER_NOT_FOUND_ERROR));

        user.setStatus(EUserStatus.ACTIVATED);
        userRepository.save(user);
    }
}

package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.dto.UserCreateDTO;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import by.itacademy.jd2.user_service.dao.api.IUserRepository;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IUserService;
import by.itacademy.jd2.user_service.service.exception.EmailAlreadyTakenException;
import by.itacademy.jd2.user_service.service.exception.ItemNotFoundException;
import by.itacademy.jd2.user_service.service.exception.VersionsNotMatchException;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private static final String USER_EXISTS_ERROR = "Пользователь с таким email уже зарегистрирован";
    private static final String USER_NOT_FOUND_ERROR = "Пользователь с таким email не найден";
    private static final String VERSIONS_NOT_MATCH_ERROR = "Версии не совпадают";
    private static final String EMAIL_NOT_FOUND_ERROR = "Email не найден";

    private final IUserRepository userRepository;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public User create(UserCreateDTO item) {
        userRepository.findByEmail(item.getEmail())
                .ifPresent(e -> {throw new EmailAlreadyTakenException(USER_EXISTS_ERROR);
        });

        return userRepository.save(Objects.requireNonNull(conversionService.convert(item, User.class)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User read(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(USER_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public User update(UUID uuid, Long version, UserCreateDTO item) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException(USER_NOT_FOUND_ERROR));

        if (!version.equals(user.getUpdateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())) {
            throw new VersionsNotMatchException(VERSIONS_NOT_MATCH_ERROR);
        }

        user.setEmail(item.getEmail());
        user.setPassword(item.getPassword());
        user.setFio(item.getFio());
        user.setRole(item.getRole());
        user.setStatus(user.getStatus());

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ItemNotFoundException(EMAIL_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public void activate(UserCreateDTO userCreateDTO) {
        User user = userRepository.findByEmail(userCreateDTO.getEmail())
                .orElseThrow(() -> new ItemNotFoundException(USER_NOT_FOUND_ERROR));

        user.setStatus(EUserStatus.ACTIVATED);
        userRepository.save(user);
    }
}

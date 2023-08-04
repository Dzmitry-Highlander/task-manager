package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.dto.UserCreateDTO;
import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.dao.api.IUserRepository;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IUserService;
import by.itacademy.jd2.user_service.service.exception.AuthException;
import by.itacademy.jd2.user_service.service.exception.EmailAlreadyTakenException;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private static final String USER_EXISTS_ERROR = "Пользователь с таким email уже зарегистрирован";
    //TODO static exception messages
    private final IUserRepository userRepository;
    private final ConversionService conversionService;

    @Override
    public User create(UserCreateDTO item) {
        userRepository.findByEmail(item.getEmail()).ifPresent(e -> {
            throw new EmailAlreadyTakenException(USER_EXISTS_ERROR);
        });

        return userRepository.save(Objects.requireNonNull(conversionService.convert(item, User.class)));
    }

    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Override
    public User read(UUID id) {
        return userRepository.findById(id)
                //TODO customException
                .orElseThrow(() -> new IllegalArgumentException("User is not found!"));
    }

    @Override
    public User update(UUID uuid, Long version, UserCreateDTO item) {
        User user = conversionService.convert(item, User.class);
        User currentUser = this.read(uuid);

        assert user != null;
        user.setUpdateDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()
        ), TimeZone.getDefault().toZoneId()));
        user.setCreateDate(currentUser.getCreateDate());

        if (user.getUpdateDate().isEqual(currentUser.getUpdateDate())) {
            return userRepository.save(user);
        } else {
            //TODO customException
            throw new RuntimeException("Versions don't match!");
        }
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> userOptional = userRepository
                .findByEmail(email);

        return conversionService.convert(
                userOptional.orElseThrow(
                        //TODO customException
                        () -> new AuthException("Email not found!")), UserDTO.class
        );
    }
}

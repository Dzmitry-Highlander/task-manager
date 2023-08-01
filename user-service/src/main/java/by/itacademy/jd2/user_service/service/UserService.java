package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.dto.UserCreateDTO;
import by.itacademy.jd2.user_service.dao.api.IUserRepository;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service //TODO спросить что это за аннотация?
@AllArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userDao;
    private final ConversionService conversionService;

    @Override
    public User create(UserCreateDTO item) {
        userDao.findByEmail(item.getEmail()).ifPresent(e -> {
            //TODO customException
            throw new RuntimeException("Email has been already taken!");
        });

        return userDao.save(Objects.requireNonNull(conversionService.convert(item, User.class)));
    }

    @Override
    public List<User> read() {
        return userDao.findAll();
    }

    @Override
    public User read(UUID id) {
        return userDao.findById(id)
                //TODO customException
                .orElseThrow(() -> new IllegalArgumentException("User is not found!"));
    }

    @Override
    public User update(UUID uuid, Long version, UserCreateDTO item) {
        User user = Objects.requireNonNull(conversionService.convert(item, User.class));
        User currentUser = this.read(Objects.requireNonNull(user.getId()));

        user.setCreateDate(currentUser.getCreateDate());

        if (user.getUpdateDate().isEqual(currentUser.getUpdateDate())) {
            return userDao.save(user);
        } else {
            //TODO customException
            throw new RuntimeException("Versions don't match!");
        }
    }
}

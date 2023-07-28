package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.dto.UserCreateDTO;
import by.itacademy.jd2.user_service.dao.api.IUserDao;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
public class UserService implements IUserService {
    private final IUserDao userDao;
    private final ConversionService conversionService;

    @Override
    public User create(UserCreateDTO item) {
        //TODO проверка email в базе или нет?
        return userDao.save(Objects.requireNonNull(conversionService.convert(item, User.class)));
    }

    @Override
    public List<User> read() {
        return userDao.findAll();
    }

    @Override
    public User read(UUID id) {
        return userDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NO NO NO!"));
    }

    //TODO update()
    @Override
    public User update(UUID uuid, Long version, UserCreateDTO item) {
        return null;
    }

    //TODO delete()
    @Override
    public void delete(UUID uuid, Long version) {

    }
}

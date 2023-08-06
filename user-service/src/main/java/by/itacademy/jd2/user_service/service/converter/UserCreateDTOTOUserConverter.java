package by.itacademy.jd2.user_service.service.converter;

import by.itacademy.jd2.user_service.core.dto.UserCreateDTO;
import by.itacademy.jd2.user_service.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserCreateDTOTOUserConverter implements Converter<UserCreateDTO, User> {
    @Override
    public User convert(UserCreateDTO source) {
        return User.builder()
                .email(source.getEmail())
                .password(source.getPassword())
                .fio(source.getFio())
                .role(source.getRole())
                .status(source.getStatus())
                .build();
    }
}

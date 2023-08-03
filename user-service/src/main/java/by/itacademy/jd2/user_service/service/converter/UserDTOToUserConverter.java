package by.itacademy.jd2.user_service.service.converter;

import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class UserDTOToUserConverter implements Converter<UserDTO, User> {
    @Override
    public User convert(UserDTO source) {
        return User.builder()
                .uuid(source.getId())
                .createDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getCreateDate()),
                        TimeZone.getDefault().toZoneId()))
                .updateDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getUpdateDate()),
                        TimeZone.getDefault().toZoneId()))
                .email(source.getEmail())
                .fio(source.getFio())
                .role(source.getRole())
                .status(source.getStatus())
                .build();
    }
}

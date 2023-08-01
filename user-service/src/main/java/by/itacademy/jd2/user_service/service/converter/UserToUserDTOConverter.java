package by.itacademy.jd2.user_service.service.converter;

import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;

public class UserToUserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User from) {
        return UserDTO.builder()
                .id(from.getId())
                .createDate(from.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .updateDate(from.getUpdateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .email(from.getEmail())
                .fio(from.getFio())
                .role(from.getRole())
                .status(from.getStatus())
                .build();
    }
}

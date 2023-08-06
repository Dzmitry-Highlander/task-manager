package by.itacademy.jd2.user_service.service.converter;

import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;

public class UserToUserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User source) {
        return UserDTO.builder()
                .id(source.getUuid())
                .createDate(source.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .updateDate(source.getUpdateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .email(source.getEmail())
                .fio(source.getFio())
                .role(source.getRole())
                .status(source.getStatus())
                .build();
    }
}

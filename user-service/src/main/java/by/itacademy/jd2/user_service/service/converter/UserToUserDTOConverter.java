package by.itacademy.jd2.user_service.service.converter;

import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;

public class UserToUserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User from) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(from.getId());
        userDTO.setCreateDate(from.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        userDTO.setUpdateDate(from.getUpdateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        userDTO.setMail(from.getMail());
        userDTO.setFio(from.getFio());
        userDTO.setRole(from.getRole());
        userDTO.setStatus(from.getStatus());

        return userDTO;
    }
}

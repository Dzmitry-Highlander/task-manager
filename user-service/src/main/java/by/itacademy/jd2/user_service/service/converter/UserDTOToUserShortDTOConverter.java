package by.itacademy.jd2.user_service.service.converter;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.user_service.core.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;

public class UserDTOToUserShortDTOConverter implements Converter<UserDTO, UserShortDTO> {
    @Override
    public UserShortDTO convert(UserDTO source) {
        return UserShortDTO.builder()
                .uuid(source.getId())
                .email(source.getEmail())
                .fio(source.getFio())
                .role(source.getRole())
                .build();
    }
}

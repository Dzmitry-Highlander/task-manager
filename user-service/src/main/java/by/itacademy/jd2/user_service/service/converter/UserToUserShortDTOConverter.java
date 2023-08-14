package by.itacademy.jd2.user_service.service.converter;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.user_service.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserToUserShortDTOConverter implements Converter<User, UserShortDTO> {
    @Override
    public UserShortDTO convert(User source) {
        return UserShortDTO.builder()
                .uuid(source.getUuid())
                .email(source.getEmail())
                .fio(source.getFio())
                .role(source.getRole())
                .build();
    }
}

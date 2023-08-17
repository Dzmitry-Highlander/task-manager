package by.itacademy.jd2.task_service.service.converter;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.User;

public class UserDetailsToUserShortDTOConverter implements Converter<User, UserShortDTO> {
    @Override
    public UserShortDTO convert(User source) {
        return UserShortDTO.builder()
                .email(source.getUsername())
                .build();
    }
}

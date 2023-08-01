package by.itacademy.jd2.user_service.service.converter;

import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.dao.entity.User;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.NonNull;

import java.time.ZoneId;
import java.util.Collections;
import java.util.Set;

public class UserToUserDTOConverterGeneric implements GenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(User.class,
                UserDTO.class));
    }

    @Override
    public Object convert(
            Object source,
            @NonNull TypeDescriptor sourceType,
            @NonNull TypeDescriptor targetType) {
        if (sourceType.getType() == User.class) {
            return source;
        }

        User user = (User) source;

        return  UserDTO.builder()
                .id(user.getId())
                .createDate(user.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .updateDate(user.getUpdateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .email(user.getEmail())
                .fio(user.getFio())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}

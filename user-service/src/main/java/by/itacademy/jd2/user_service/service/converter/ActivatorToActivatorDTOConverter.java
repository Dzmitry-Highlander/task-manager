package by.itacademy.jd2.user_service.service.converter;

import by.itacademy.jd2.user_service.core.dto.ActivatorDTO;
import by.itacademy.jd2.user_service.dao.entity.Activator;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;

public class ActivatorToActivatorDTOConverter implements Converter<Activator, ActivatorDTO> {
    @Override
    public ActivatorDTO convert(Activator source) {
        return ActivatorDTO.builder()
                .id(source.getId())
                .code(source.getCode())
                .email(source.getEmail())
                .createDate(source.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .expirationDate(source.getExpirationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .build();
    }
}

package by.itacademy.jd2.user_service.service.converter;

import by.itacademy.jd2.user_service.core.dto.ActivatorCreateDTO;
import by.itacademy.jd2.user_service.dao.entity.Activator;
import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class ActivatorCreateDTOtoActivatorConverter implements Converter<ActivatorCreateDTO, Activator> {
    @Override
    public Activator convert(ActivatorCreateDTO source) {
        return Activator.builder()
                .email(source.getEmail())
                .code(source.getCode())
                .createDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getCreateDate()),
                        TimeZone.getDefault().toZoneId()))
                .expirationDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getExpirationDate()),
                        TimeZone.getDefault().toZoneId()))
                .build();
    }
}

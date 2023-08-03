package by.itacademy.jd2.user_service.service.converter;

import by.itacademy.jd2.user_service.core.dto.ActivatorDTO;
import by.itacademy.jd2.user_service.dao.entity.Activator;
import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

public class ActivatorDTOtoActivatorConverter implements Converter<ActivatorDTO, Activator> {
    @Override
    public Activator convert(ActivatorDTO source) {
        return Activator.builder()
                .id(source.getId())
                .email(source.getEmail())
                .createDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getCreateDate()),
                        TimeZone.getDefault().toZoneId()))
                .expirationDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getExpirationDate()),
                        TimeZone.getDefault().toZoneId()))
                .build();
    }
}

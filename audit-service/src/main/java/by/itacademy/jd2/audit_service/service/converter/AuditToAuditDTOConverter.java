package by.itacademy.jd2.audit_service.service.converter;

import by.itacademy.jd2.base_package.core.dto.AuditDTO;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;

public class AuditToAuditDTOConverter implements Converter<Audit, AuditDTO> {
    @Override
    public AuditDTO convert(Audit source) {
        return AuditDTO.builder()
                .uuid(source.getUuid())
                .createDate(source.getCreateDate()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli())
                .user(UserShortDTO.builder()
                        .uuid(source.getUserId())
                        .email(source.getEmail())
                        .fio(source.getFio())
                        .role(source.getRole())
                        .build())
                .text(source.getText())
                .type(source.getType())
                .id(source.getId())
                .build();
    }
}

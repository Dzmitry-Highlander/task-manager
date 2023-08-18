package by.itacademy.jd2.audit_service.service.converter;

import by.itacademy.jd2.audit_service.dao.entity.Audit;
import by.itacademy.jd2.base_package.core.dto.AuditCreateDTO;
import org.springframework.core.convert.converter.Converter;

public class AuditCreateDTOToAuditConverter implements Converter<AuditCreateDTO, Audit> {
    @Override
    public Audit convert(AuditCreateDTO source) {
        return Audit.builder()
                .userId(source.getUser().getUuid())
                .email(source.getUser().getMail())
                .fio(source.getUser().getFio())
                .role(source.getUser().getRole())
                .text(source.getText())
                .type(source.getType())
                .id(source.getId())
                .build();
    }
}

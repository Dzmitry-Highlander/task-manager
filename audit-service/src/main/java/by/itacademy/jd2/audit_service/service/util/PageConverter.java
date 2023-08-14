package by.itacademy.jd2.audit_service.service.util;

import by.itacademy.jd2.audit_service.core.dto.AuditDTO;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import by.itacademy.jd2.base_package.core.dto.PageDTO;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;

public class PageConverter {
    public static PageDTO<AuditDTO> convert(Page<Audit> users, ConversionService conversionService) {
        return new PageDTO<>(users.getNumber(),
                users.getSize(),
                users.getTotalPages(),
                users.getTotalElements(),
                users.isFirst(),
                users.getNumberOfElements(),
                users.isLast(),
                users.get().map(u -> conversionService.convert(u, AuditDTO.class)).toList());
    }
}

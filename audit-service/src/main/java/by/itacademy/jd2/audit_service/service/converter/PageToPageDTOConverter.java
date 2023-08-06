package by.itacademy.jd2.audit_service.service.converter;

import by.itacademy.jd.base_pakage.core.dto.PageDTO;
import by.itacademy.jd2.audit_service.core.dto.AuditDTO;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;

@NoArgsConstructor
public class PageToPageDTOConverter {
    public static PageDTO<AuditDTO> convert(Page<Audit> users, ConversionService conversionService){
        return new PageDTO<>(
                users.getNumber(),
                users.getSize(),
                users.getTotalPages(),
                users.getTotalElements(),
                users.isFirst(),
                users.getNumberOfElements(),
                users.isLast(),
                users.get().map(user -> conversionService.convert(user, AuditDTO.class)).toList()
        );
    }
}

package by.itacademy.jd2.audit_service.service.converter;

import by.itacademy.jd.base_pakage.core.dto.PageDTO;
import by.itacademy.jd2.audit_service.core.dto.AuditDTO;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
public class PageToPageDTOConverter implements Converter<Page<Audit>, PageDTO<AuditDTO>> {
    private final ConversionService conversionService;

    @Override
    public PageDTO<AuditDTO> convert(Page<Audit> source) {
        return new PageDTO<>(
                source.getNumber(),
                source.getSize(),
                source.getTotalPages(),
                source.getTotalElements(),
                source.isFirst(),
                source.getNumberOfElements(),
                source.isLast(),
                source.get().map(user -> conversionService.convert(user, AuditDTO.class)).toList()
        );
    }
}

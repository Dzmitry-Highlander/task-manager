package by.itacademy.jd2.task_service.service.util;

import by.itacademy.jd2.base_package.core.dto.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageConverter {
    private final ConversionService conversionService;

    public <T, S> PageDTO<S> convertPageToDTO(Page<T> page, Class<S> dtoClass) {
        return new PageDTO<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.getNumberOfElements(),
                page.isLast(),
                page.get().map(u -> conversionService.convert(u, dtoClass)).toList());
    }
}

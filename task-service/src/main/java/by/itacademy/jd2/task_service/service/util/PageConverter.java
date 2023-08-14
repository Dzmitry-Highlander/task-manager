package by.itacademy.jd2.task_service.service.util;

import by.itacademy.jd2.base_package.core.dto.PageDTO;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;

public class PageConverter {
    public static <T, S> PageDTO<S> convert(
            Page<T> page,
            Class<S> dtoClass,
            ConversionService conversionService
    ) {
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

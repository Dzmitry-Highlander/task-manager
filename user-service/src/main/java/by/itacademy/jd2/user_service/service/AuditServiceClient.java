package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.base_package.core.dto.AuditCreateDTO;
import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.base_package.core.enums.EEssenceType;
import by.itacademy.jd2.user_service.service.api.IAuditService;
import by.itacademy.jd2.user_service.service.api.IUserHolder;
import by.itacademy.jd2.user_service.service.feign.IAuditServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class AuditServiceClient implements IAuditService {
    private final IAuditServiceClient feignClientService;
    private final IUserHolder userHolder;
    private final ConversionService conversionService;

    @Override
    public void send(String text, String id) {
        feignClientService.send(
                AuditCreateDTO.builder()
                        .user(conversionService.convert(userHolder.getUser(), UserShortDTO.class))
                        .text(text)
                        .type(EEssenceType.USER)
                        .id(id)
                        .build()
        );
    }
}

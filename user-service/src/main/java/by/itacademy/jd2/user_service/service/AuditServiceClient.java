package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.base_package.core.dto.AuditCreateDTO;
import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.base_package.core.enums.EEssenceType;
import by.itacademy.jd2.user_service.service.api.IAuditService;
import by.itacademy.jd2.user_service.service.feign.IAuditServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditServiceClient implements IAuditService {
    private final IAuditServiceClient feignClientService;

    @Override
    public void send(UserShortDTO userShortDTO, String text, String id) {
        feignClientService.send(
                AuditCreateDTO.builder()
                        .user(userShortDTO)
                        .text(text)
                        .type(EEssenceType.USER)
                        .id(id)
                        .build()
        );
    }
}

package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.base_package.core.dto.AuditCreateDTO;
import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.base_package.core.enums.EEssenceType;
import by.itacademy.jd2.task_service.service.api.IAuditService;
import by.itacademy.jd2.task_service.service.feign.IAuditFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService implements IAuditService {
    private final IAuditFeignClient auditFeignClient;

    @Override
    public void send(UserShortDTO editor, String text, EEssenceType type, String id) {
        auditFeignClient.send(
                AuditCreateDTO.builder()
                        .user(editor)
                        .text(text)
                        .type(type)
                        .id(id)
                        .build()
        );
    }
}

package by.itacademy.jd2.task_service.service;

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
    public void save(UserShortDTO editor, String action, String id, EEssenceType type) {

    }

    @Override
    public void save(String action, String id, EEssenceType type) {

    }
}

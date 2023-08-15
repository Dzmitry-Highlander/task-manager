package by.itacademy.jd2.task_service.service.api;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.base_package.core.enums.EEssenceType;

public interface IAuditService {
    void save(UserShortDTO editor, String action, String id, EEssenceType type);

    void save(String action, String id, EEssenceType type);
}

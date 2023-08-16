package by.itacademy.jd2.task_service.service.api;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.base_package.core.enums.EEssenceType;

public interface IAuditService {
    void send(UserShortDTO editor, String text, EEssenceType type, String id);
}

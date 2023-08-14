package by.itacademy.jd2.user_service.service.api;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;

public interface IAuditService {
    void send(UserShortDTO userShortDTO, String text, String id);
}

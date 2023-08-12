package by.itacademy.jd2.audit_service.service.api;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;

public interface IUserInteractService {
    UserShortDTO sendAndGet(String bearerToken);
}

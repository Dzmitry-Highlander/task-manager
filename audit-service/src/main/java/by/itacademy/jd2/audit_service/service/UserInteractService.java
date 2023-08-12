package by.itacademy.jd2.audit_service.service;

import by.itacademy.jd2.audit_service.service.api.IUserInteractService;
import by.itacademy.jd2.audit_service.service.feign.UserServiceClient;
import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInteractService implements IUserInteractService {
    private final UserServiceClient userServiceClient;

    @Override
    public UserShortDTO sendAndGet(String bearerToken) {
        return userServiceClient.send("Bearer " + bearerToken).getBody();
    }
}

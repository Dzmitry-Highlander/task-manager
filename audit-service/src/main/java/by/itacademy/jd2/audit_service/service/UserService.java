package by.itacademy.jd2.audit_service.service;

import by.itacademy.jd2.audit_service.service.api.IUserService;
import by.itacademy.jd2.audit_service.service.feign.IUserFeignClient;
import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserFeignClient userFeignClient;

    public UserShortDTO getMe(String jwt) {
        return this.userFeignClient.getMe("Bearer " + jwt).getBody();
    }
}

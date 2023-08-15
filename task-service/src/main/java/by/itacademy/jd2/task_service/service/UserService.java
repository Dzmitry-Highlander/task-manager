package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.task_service.service.api.IUserService;
import by.itacademy.jd2.task_service.service.feign.IUserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserFeignClient userFeignClient;

    @Override
    public UserShortDTO getMe(String jwt) {
        return userFeignClient.getMe("Bearer " + jwt).getBody();
    }
}

package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.task_service.core.dto.UserRefDTO;
import by.itacademy.jd2.task_service.service.api.IUserService;
import by.itacademy.jd2.task_service.service.feign.IUserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserFeignClient userFeignClient;
    private final JwtService jwtService;

    @Override
    public UserShortDTO getMe(String jwt) {
        return userFeignClient.getMe("Bearer " + jwt).getBody();
    }

    @Override
    public UserShortDTO get(UserRefDTO user, String jwt) {
        String systemToken = jwtService.generateSystemAccessToken("task-service");

        return userFeignClient.get("Bearer " + systemToken, user.getUuid()).getBody();
    }

    @Override
    public List<UserShortDTO> get(List<UserRefDTO> staff, String jwt) {
        return null;
    }
}

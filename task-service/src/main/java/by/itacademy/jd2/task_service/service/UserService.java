package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.task_service.core.dto.UserRefDTO;
import by.itacademy.jd2.task_service.service.api.IUserService;
import by.itacademy.jd2.task_service.service.feign.IUserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserFeignClient userFeignClient;

    @Override
    public UserShortDTO getMe(String jwt) {
        return userFeignClient.getMe("Bearer " + jwt).getBody();
    }

    @Override
    public UserShortDTO get(UserRefDTO user, String jwt) {
        return userFeignClient.get("Bearer " + jwt, user.getUuid()).getBody();
    }

    @Override
    public List<UserShortDTO> get(List<UserRefDTO> staff, String jwt){
        List<UUID> uuids = staff.stream().map(UserRefDTO::getUuid).toList();

        return userFeignClient.get("Bearer " + jwt, uuids).getBody();
    }
}

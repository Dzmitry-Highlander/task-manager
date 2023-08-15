package by.itacademy.jd2.task_service.service.feign;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "user-service", url = "http://user-service:8080")
public interface IUserFeignClient {
    @GetMapping("/users/me")
    ResponseEntity<UserShortDTO> getMe(@RequestHeader("Authorization") String jwt);

    @GetMapping("/users/{uuid}")
    ResponseEntity<UserShortDTO> get(@RequestHeader("Authorization") String jwt, @PathVariable UUID uuid);

    @PostMapping("/internal/users")
    ResponseEntity<List<UserShortDTO>> get(@RequestHeader("Authorization") String jwt, @RequestBody List<UUID> users);
}

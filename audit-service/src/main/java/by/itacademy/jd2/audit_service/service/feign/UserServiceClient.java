package by.itacademy.jd2.audit_service.service.feign;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-service", url = "http://user-service:8080/users/me")
public interface UserServiceClient {

    @GetMapping
    ResponseEntity<UserShortDTO> send(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken);
}

package by.itacademy.jd2.task_service.service.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service", url = "http://user-service:8080")
public interface IAuditFeignClient {
}

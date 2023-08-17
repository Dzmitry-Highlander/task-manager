package by.itacademy.jd2.task_service.service.api;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserHolder {
    UserDetails getUser();
}

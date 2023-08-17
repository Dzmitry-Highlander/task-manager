package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.task_service.service.api.IUserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHolder implements IUserHolder {
    private final UserDetailsService userDetailsService;

    @Override
    public UserDetails getUser() {
        return userDetailsService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}

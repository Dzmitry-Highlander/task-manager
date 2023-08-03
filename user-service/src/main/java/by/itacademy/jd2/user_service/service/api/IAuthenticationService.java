package by.itacademy.jd2.user_service.service.api;

import by.itacademy.jd2.user_service.core.dto.AuthenticationResponseDTO;
import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.core.dto.UserLoginDTO;
import by.itacademy.jd2.user_service.core.dto.UserRegistrationDTO;

public interface IAuthenticationService {
    AuthenticationResponseDTO register(UserRegistrationDTO request);

    AuthenticationResponseDTO login(UserLoginDTO request);

    String verification(String code, String mail);

    UserDTO me(String email);
}

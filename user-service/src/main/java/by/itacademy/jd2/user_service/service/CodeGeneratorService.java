package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.dto.ActivatorDTO;
import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.service.api.ICodeGeneratorService;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CodeGeneratorService implements ICodeGeneratorService {
    @Override
    public ActivatorDTO generate(UserDTO userDTO) {
        Random code = new Random();
        int min = 1000;
        int max = 9999;

        return ActivatorDTO.builder()
                .email(userDTO.getEmail())
                .code(code.nextInt(max - min + 1) + min)
                .createDate(System.currentTimeMillis())
                .expirationDate(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(7))
                .build();
    }
}

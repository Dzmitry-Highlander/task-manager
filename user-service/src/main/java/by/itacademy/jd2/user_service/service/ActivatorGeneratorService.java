package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.dto.ActivatorDTO;
import by.itacademy.jd2.user_service.service.api.IActivatorGenerator;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ActivatorGeneratorService implements IActivatorGenerator {
    @Override
    public ActivatorDTO generate(String email) {
        Random random = new Random();
        int min = 1000;
        int max = 9999;

        return ActivatorDTO.builder()
                .email(email)
                .code(random.nextInt(max - min + 1) + min)
                .createDate(System.currentTimeMillis())
                .expirationDate(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30))
                .build();
    }
}

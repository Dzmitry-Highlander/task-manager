package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.service.api.ICodeGeneratorService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeGeneratorService implements ICodeGeneratorService {
    @Override
    public String generate() {
        Random random = new Random();
        int min = 1000;
        int max = 9999;

        return String.valueOf(random.nextInt(max - min + 1) + min);
    }
}

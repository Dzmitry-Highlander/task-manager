package by.itacademy.jd2.user_service.service.util;

import java.util.Random;

public class CodeGenerator {
    public static String generate() {
        Random random = new Random();
        int min = 1000;
        int max = 9999;

        return String.valueOf(random.nextInt(max - min + 1) + min);
    }
}

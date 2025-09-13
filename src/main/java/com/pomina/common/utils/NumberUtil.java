package com.pomina.common.utils;

import java.util.Random;

public class NumberUtil {

    private NumberUtil() {}

    public static String generateNumber(int lengthNumber) {
        Random random = new Random();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < lengthNumber; i++) {
            number.append(random.nextInt(10));
        }

        return number.toString();
    }
}

package md.util;

import java.util.Random;

public class Utilities {
    private static final Random random = new Random();

    private Utilities() {
        // Приватный конструктор для предотвращения создания экземпляра класса
    }

    public static double generateRandomSalary(double maxSalaryValue, double minSalaryValue) {
        if (minSalaryValue > maxSalaryValue) {
            throw new IllegalArgumentException("Минимальная зарплата не может быть больше максимальной");
        }
        return minSalaryValue + (maxSalaryValue - minSalaryValue) * random.nextDouble();
    }
}


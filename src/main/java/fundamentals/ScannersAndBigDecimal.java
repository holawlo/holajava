package fundamentals;

import java.util.Scanner;
import java.math.BigDecimal;

public class ScannersAndBigDecimal {

    public static void main(String[] args) {
        scannerExample();
        double x = 0.02;
        double y = 0.03;
        BigDecimal xxx = new BigDecimal(x);
        BigDecimal yyy = new BigDecimal("0.03");
        BigDecimal zzz = xxx.subtract(yyy);
        System.out.println(zzz);

    }

    private static void scannerExample() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadz pierwsza liczbe: ");
        double firstValue = scanner.nextDouble();
        System.out.println("Wprowadz druga liczbe: ");
        double secondValue = scanner.nextDouble();
        System.out.println("a+b=" + (firstValue + secondValue));
    }
}

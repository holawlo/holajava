package fundamentals;

import java.util.Arrays;

public class LoopsAndArrays {
    //break to exit loop, continue to skip to next iter

    public static void main(String[] args) {
        isEvenTernary(5);
        arraysToString();
        fibonacci(7);
        printTree(6);
    }

    private static void arraysToString() {
        int[] emptyTable = new int[9];
        for (int i = 0; i < emptyTable.length; i++) {
            emptyTable[i] = i + 1;
        }
        System.out.println(Arrays.toString(emptyTable));
    }

    public static boolean isEvenTernary(int a) {
        boolean result = a % 2 == 0 ? true : false;
        return result;
    }

    private static void fibonacci(int numberOfElements) {
        int first = 0;
        int second = 1;
        int counter = 1;
        System.out.println(0);
        System.out.println(1);

        while (counter < numberOfElements) {
            int sum = first + second;
            System.out.println(sum);
            first = second;
            second = sum;
            counter++;
        }

    }

    public static void countDownFor(int start) {
        System.out.println("Parametr = " + start);
        for (int i = start; i >= 0; i--) {
            System.out.println(i);
        }
    }

    public static void countDownWhile(int start) {
        System.out.println("Parametr while = " + start);
        while (start >= 0) {
            System.out.println(start);
            start--;
        }
    }

    public static void countDownDoWhile(int start) {
        System.out.println("Parametr = " + start);
        if (start <= 0) {
            System.out.println("Nieprawidłowy parametr " + start);
            return;
        }
        do {
            System.out.println(start);
            start--;
        } while (start >= 0);
    }

    public static void printTree(int height2) {
        for (int i = 0; i < height2; i++) {//wiersze
            for (int j = 0; j <= i; j++) {//kolumny
                System.out.print("." + i);
            }
            System.out.println();
        }
    }

}

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProteinsChecker {
    @Test
    void check() {
        String path = "C:/projects/lancuchy_bialkowe_dane.txt";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String firstLine = scanner.nextLine();
            String secondLine = scanner.nextLine();
            if (firstLine.length() == secondLine.length()) {
                char[] firstArray = firstLine.toCharArray();
                Arrays.sort(firstArray);
                char[] secondArray = secondLine.toCharArray();
                Arrays.sort(secondArray);
                System.out.println(Arrays.equals(firstArray, secondArray));
            } else {
                System.out.println("false");
            }
        }
        System.out.println("KONIEC 1");
    }

    private String data =
            "BDDFPQPPRRAGGHPOPDKJKPEQAAER\n" +
                    "BDDFPQPFRRAGGHPOPDKJKPEQAAER\n" +
                    "BDDFPQPFRRAGCHPOPDKJKPEQAAER\n" +
                    "BDDFPQPFRRAGGHPOPDKJKPEQAAER\n" +
                    "BDDFPOPFRRAGCHPOPDKJKPEQAAER\n" +
                    "BDDFPOPFRRAGCHPOPDKJKPEQAAER\n" +
                    "AABBCC\n" +
                    "ACBBCA\n" +
                    "BCBACA\n" +
                    "ACBBCA\n" +
                    "AABBCC\n" +
                    "BCBACA\n" +
                    "BCBACA\n" +
                    "AABBCCC\n" +
                    "AABBCC\n" +
                    "AABBCC\n" +
                    "ABBBCC\n" +
                    "AABCCC\n" +
                    "ABCAC\n" +
                    "CACBA\n" +
                    "AAAAAAAAAAAAAAAAAAAAB\n" +
                    "AAAAAAAAAAAAAAAAAAAAA\n" +
                    "QOOOOOOOOOOOOOOOOOOOO\n" +
                    "OOOOOOOOOOOOOOOOOOOOQ";

    @Test // hehehehe
    void check2() {
        List<char[]> chars = Arrays.stream(data.split("\\n")).parallel().map(s -> s.toCharArray()).peek(s -> Arrays.parallelSort(s)).collect(Collectors.toList());
//        for (int i = 0; i < chars.size() - 1; i = i + 2) {
//            System.out.println(Arrays.equals(chars.get(i), chars.get(i + 1)));
//        }
        IntStream.iterate(0, n -> n + 2).parallel().limit(chars.size() / 2).forEachOrdered(i -> System.out.println(Thread.currentThread().getName()+" "+Arrays.equals(chars.get(i), chars.get(i + 1))));


        System.out.println("KONIEC 2");
    }

}

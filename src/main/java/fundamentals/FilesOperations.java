package fundamentals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesOperations {

    private static final String PATH_TO_FILE = "/home/ola/Desktop/wiersz.txt";
    private static final String PATH_TO_FILE_2 = "/home/ola/Desktop/romeo_juliet.txt";

    public static void main(String[] args) {
        notNiceFileReadWithManyTries();
        niceReadFileExampleTryWithResources();
        countWords();
    }

    private static void countWords() {

        try (FileReader fileReader = new FileReader(PATH_TO_FILE_2)) {

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineToCount = bufferedReader.readLine();
            int counter = 0;

            while (lineToCount != null) {
                String[] words = lineToCount.split(" ");
                for (String w : words) {
                    counter++;
                }
                lineToCount = bufferedReader.readLine();
            }

            System.out.println(counter);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private static void niceReadFileExampleTryWithResources() {
        try (FileReader fileReader = new FileReader(PATH_TO_FILE)) { //try with resources
            readLinesInner(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void notNiceFileReadWithManyTries() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(PATH_TO_FILE);
            readLinesInner(fileReader);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //ABSOLUTNIE NIGDY NIE ZOSTAWIAJCIE PUSTEGO KECZA!!!
        } finally {
            if(fileReader!=null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void printCapitalized(List<String> lines) {
        for (String s : lines) {
            String c = String.valueOf(s.charAt(0));
            String upperCased = c.toUpperCase();
            String restOfLine = s.substring(1);
            System.out.println(upperCased + restOfLine);

//                System.out.println(StringUtils.capitalize(s)); alternatywa
        }
    }

    private static void readLinesInner(FileReader fileReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        printCapitalized(lines);
    }
}

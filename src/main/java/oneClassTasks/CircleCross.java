package oneClassTasks;

import java.util.Scanner;

public class CircleCross { //TODO out of range position given

    private static String[] gameArray = new String[9];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        fillStartArray();
        ifGameFinished();
        goOnWithGame();
    }

    private static void printGameArray() {
        System.out.println(gameArray[0] + " " + gameArray[1] + " " + gameArray[2]);
        System.out.println(gameArray[3] + " " + gameArray[4] + " " + gameArray[5]);
        System.out.println(gameArray[6] + " " + gameArray[7] + " " + gameArray[8]);
    }

    private static void ifGameFinished() {

        if (gameArray[0].equals(gameArray[1]) && (gameArray[0].equals(gameArray[2])) && (gameArray[0].equals("X") || gameArray[0].equals("O"))) {
            System.out.println(gameArray[0] + " won");
            System.exit(0);
        }
        if (gameArray[0].equals(gameArray[3]) && (gameArray[0].equals(gameArray[6])) && (gameArray[0].equals("X") || gameArray[0].equals("O"))) {
            System.out.println(gameArray[0] + " won");
            System.exit(0);
        }
        if (gameArray[0].equals(gameArray[4]) && (gameArray[0].equals(gameArray[8])) && (gameArray[0].equals("X") || gameArray[0].equals("O"))) {
            System.out.println(gameArray[0] + " won");
            System.exit(0);
        }
        if (gameArray[1].equals(gameArray[4]) && (gameArray[1].equals(gameArray[7])) && (gameArray[1].equals("X") || gameArray[1].equals("O"))) {
            System.out.println(gameArray[0] + " won");
            System.exit(0);
        }
        if (gameArray[2].equals(gameArray[4]) && (gameArray[2].equals(gameArray[6])) && (gameArray[2].equals("X") || gameArray[2].equals("O"))) {
            System.out.println(gameArray[0] + " won");
            System.exit(0);
        }
        if (gameArray[2].equals(gameArray[5]) && (gameArray[2].equals(gameArray[8])) && (gameArray[2].equals("X") || gameArray[2].equals("O"))) {
            System.out.println(gameArray[0] + " won");
            System.exit(0);
        }
        if (gameArray[3].equals(gameArray[4]) && (gameArray[3].equals(gameArray[5])) && (gameArray[3].equals("X") || gameArray[3].equals("O"))) {
            System.out.println(gameArray[0] + " won");
            System.exit(0);
        }
    }

    private static void goOnWithGame() {

        for (int i = 0; i < 9; i++) {

            if (i % 2 == 0) {
                oMove();
            } else {
                xMove();
            }
        }
    }

    private static void xMove() {
        System.out.println("Give position (from 1 to 9) for X");
        Integer position = scanner.nextInt();

        if (gameArray[position - 1].equals("-")) {
            gameArray[position - 1] = "X";
            printGameArray();
            ifGameFinished();
        } else {
            System.out.println("This position has been filled yet. Give another one:");
            xMove();
        }
    }

    private static void oMove() {
        System.out.println("Give position (from 1 to 9) for O");
        Integer position = scanner.nextInt();

        if (gameArray[position - 1].equals("-")) {
            gameArray[position - 1] = "O";
            printGameArray();
            ifGameFinished();
        } else {
            System.out.println("This position has been filled yet. Give another one:");
            oMove();
        }
    }

    private static void fillStartArray() {
        for (int i = 0; i < gameArray.length; i++) {
            gameArray[i] = "-";
        }
        printGameArray();
    }
}

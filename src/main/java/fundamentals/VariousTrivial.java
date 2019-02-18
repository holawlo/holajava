package fundamentals;

import java.time.LocalDateTime;
import java.util.Random;

public class VariousTrivial {

    LocalDateTime actualTime = LocalDateTime.now();
    int hour = actualTime.getHour();

    public static int[] prepareRandomArray(int howLong) {
        int[] tabForRandNum = new int[howLong];
        Random random = new Random();
        for (int i = 0; i < howLong; i++) {
            tabForRandNum[i] = random.nextInt();
        }
        return tabForRandNum;
    }



    // blok inicjalizujacy
   /* static {
        counter = 1;
    }

    {
        System.out.println("Blok inicjalizujacy");
        this.identity = counter++;
    }*/

}

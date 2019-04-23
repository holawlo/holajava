package fundamentals.threads;

import java.util.Random;

public class ClientActionRunnable implements Runnable{

    @Override
    public void run() {
        int howMuch = new Random().nextInt(101);
        Bank.withdraw(howMuch);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bank.deposit(howMuch);
    }
}

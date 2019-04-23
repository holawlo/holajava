package fundamentals.threads;

public class OurRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " lambda runnable");
    }
}

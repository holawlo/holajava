package fundamentals.threads;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ThreadsExample {
    @Test
    void runnableBasics() {
        //tworzymy zadania
        Runnable lambdaRunnable = () -> System.out.println(Thread.currentThread().getName() + " lambda runnable");
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " lambda runnable");
            }
        };
        Runnable ourRunnable = new OurRunnable();
        Thread thread1 = new Thread(lambdaRunnable);
        Thread thread2 = new Thread(anonymousRunnable);
        Thread thread3 = new Thread(ourRunnable);
        thread1.start(); //run odpali się w wątku z którego jest uruchamiany
        thread2.start();
        thread3.start();
        System.out.println();
    }

    @Test
    void bankSequential() {
        for (int i = 0; i < 100; i++) {
            Runnable car = new ClientActionRunnable();
            car.run();
        }
    }

    @Test
    void bankWithThreads() {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Runnable car = new ClientActionRunnable();
            Thread thread = new Thread(car);
            threadList.add(thread);
//            thread.start();
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        threadList.stream()
                .forEach(t-> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        System.out.println(" liczba operacji: " + Bank.counter);
    }
}

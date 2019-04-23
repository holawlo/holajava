package fundamentals.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class Bank {
    private static Integer balance = 10000;
    public static AtomicInteger counter = new AtomicInteger(0);

    public synchronized static void withdraw(Integer howMuch){
        balance = balance - howMuch;
        System.out.println(Thread.currentThread().getName() + " stan konta po wypłacie: " + balance);
    }
    public synchronized static void deposit(Integer howMuch){
        balance = balance + howMuch;
        counter.getAndIncrement();
//        synchronized (Bank.class) { blok synchronized pozwala na działanie na Integerze
//            counter = ++counter;
//        }
        System.out.println(Thread.currentThread().getName() + " stan konta po wpłacie: " + balance);
    }
}
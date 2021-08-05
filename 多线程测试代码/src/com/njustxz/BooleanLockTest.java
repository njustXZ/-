package com.njustxz;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

public class BooleanLockTest {
    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        try {
            lock.lock();
            System.out.println(currentThread() + "get the Lock");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unLock();
        }
    }

    public static void main(String[] args){
        BooleanLockTest blt = new BooleanLockTest();
        /*IntStream.range(0, 10)
                .mapToObj(i -> new Thread(blt::syncMethod))
                .forEach(Thread::start);*/
        new Thread(blt::syncMethod,"T1").start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("T1 is working");
        }
        Thread t2 = new Thread(blt::syncMethod,"T2");
        t2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("t2 is blocked,now it is out of block");
        }
        t2.interrupt();
    }
}

package com.njustxz;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用volatile来保证共享变量是互相可见的；
 * volatile除了保障可见性之外，还可以保证顺序性
 * volatile修饰语句之前的语句保证了一定执行过了
 */
public class VolatileTest {
    final static int Max = 5;
    static volatile int init_val = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = init_val;
            while (localValue < Max) {
                if (localValue != init_val) {
                    System.out.println("init_value is changed to :" + init_val);
                }
                localValue = init_val;
            }
        }, "Reader").start();

        new Thread(() -> {
            int localValue = init_val;
            while (localValue < Max) {
                System.out.println("init_val is going to be:" + ++localValue);
                init_val = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}
class VolatileTest1{
    private static volatile int i=0;
    private static final CountDownLatch LATCH = new CountDownLatch(10);
    private static void inc(){
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10; j++) {
            new Thread(()->{
                for (int x = 0; x < 1000; x++) {
                    inc();
                }
                LATCH.countDown();
            }).start();
        }
        LATCH.await();
        System.out.println(i);
    }
}

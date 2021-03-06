package com.njustxz.hook;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UncaughtException {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            System.out.println(t.getName()+" occur exception:");
            System.out.println(e.getMessage());
        });
        Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1/0);
        },"Test-thread");
        thread.start();

        try{
            System.out.println("111111");
            System.out.println(100/0);
            System.out.println("++++++++++++++++");
        }catch(Exception e){

            e.printStackTrace();
        }
        System.out.println("22222");
    }
}

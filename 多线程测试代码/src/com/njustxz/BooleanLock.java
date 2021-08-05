package com.njustxz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;

public class BooleanLock implements Lock {
    private Thread currentThread;
    private Boolean locked = false;
    private final List<Thread> blockThread = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                if(!blockThread.contains(currentThread())) blockThread.add(currentThread());
                this.wait();
            }
            blockThread.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(Long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            } else {
                Long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get the lock during " + mills + "ms.");
                    }
                    if(!blockThread.contains(currentThread())) blockThread.add(currentThread());
                    this.wait(remainingMills);
                    remainingMills = endMills-System.currentTimeMillis();
                }
                blockThread.remove(currentThread());
                this.locked=true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unLock() {
        synchronized (this)
        {
            if(currentThread == currentThread()){
                this.locked=false;
                Optional.of(currentThread().getName()+" release the lock.").ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockThread() {
        return Collections.unmodifiableList(blockThread);
    }
}

package com.njustxz;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface Lock {
    void lock() throws InterruptedException;
    void lock(Long mills) throws InterruptedException, TimeoutException;
    void unLock();
    List<Thread> getBlockThread();
}

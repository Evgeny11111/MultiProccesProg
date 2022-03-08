package ru.sbt.edu.concurrency.counter;

import java.util.concurrent.Semaphore;

public class ConcurrentCounter implements Counter{
    private long value;
    private final Semaphore semaphore;

    public ConcurrentCounter() {
        this.semaphore = new Semaphore(1,true);
        this.value = 0;
    }

    @Override
    public void increment() {
        try {
            semaphore.acquire();
            value++;
            semaphore.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public long getValue() {
        return value;
    }
}

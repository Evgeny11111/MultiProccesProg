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
        }catch (Exception e){
            e.printStackTrace();
        }
        semaphore.release();
    }

    @Override
    public long getValue() {
        return value;
    }
}

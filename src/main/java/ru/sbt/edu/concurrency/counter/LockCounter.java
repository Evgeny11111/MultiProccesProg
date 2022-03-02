package ru.sbt.edu.concurrency.counter;

import ru.sbt.edu.concurrency.locks.theory.LockTwo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter implements Counter{
    private long value;
    private final Lock lock;

    public LockCounter() {
        this.value = 0;
        this.lock = new ReentrantLock();
    }

    @Override
    public void increment() {
        lock.lock();
        try {
            value++;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public long getValue() {
        return value;
    }
}

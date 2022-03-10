package ru.sbt.edu.concurrency.counter;

import ru.sbt.edu.concurrency.locks.ILock;
import ru.sbt.edu.concurrency.locks.theory.BuckeryLock;

public class MagicCounter implements Counter {
    private final ILock buckeryLock;
    private long value;

    public MagicCounter(int n) {
        value = 0;
        buckeryLock = new BuckeryLock(n);
    }

    @Override
    public void increment() {
        buckeryLock.lock();
        try {
            value++;
        } finally {
            buckeryLock.unlock();
        }
    }

    @Override
    public long getValue() {
            return value;
    }

}

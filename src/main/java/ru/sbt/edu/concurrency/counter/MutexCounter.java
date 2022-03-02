package ru.sbt.edu.concurrency.counter;

public class MutexCounter implements Counter{
    private long value;

    public MutexCounter() {
        this.value = 0;
    }

    @Override
    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    @Override
    public long getValue() {
        synchronized (this) {
            return value;
        }
    }
}

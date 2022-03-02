package ru.sbt.edu.concurrency.locks.theory;

import ru.sbt.edu.concurrency.locks.ILock;
import ru.sbt.edu.concurrency.util.ThreadID;
import ru.sbt.edu.concurrency.util.TwoThreadIds;

public class LockTwo implements ILock {
    private int victim;

    @Override
    public void lock() {
        int i = ThreadID.get();
        victim = i;
        while (victim == i){
        }
    }


    @Override
    public void unlock() {
    }
}

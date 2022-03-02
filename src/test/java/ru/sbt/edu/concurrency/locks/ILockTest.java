package ru.sbt.edu.concurrency.locks;

import org.junit.Test;
import ru.sbt.edu.concurrency.counter.*;
import ru.sbt.edu.concurrency.locks.theory.LockOne;
import ru.sbt.edu.concurrency.locks.theory.LockTwo;
import ru.sbt.edu.concurrency.locks.theory.LockZero;
import ru.sbt.edu.concurrency.locks.theory.PetersonLock;
import ru.sbt.edu.concurrency.util.TwoThreadIds;

import java.util.concurrent.locks.Lock;

import static junit.framework.TestCase.assertEquals;

public class ILockTest {
    @Test
    public void testTheoryLock()  {
        ILock lock = new LockZero();
        ILock lock1 = new LockOne();
        ILock lock2 = new LockTwo();
        ILock lock3 = new PetersonLock();

        Counter counter = new ILockCounter(lock);
        Counter counter1 = new ILockCounter(lock1);
        Counter counter2 = new ILockCounter(lock2);
        Counter counter3 = new ILockCounter(lock3);

        //try: 1, 2, 10, 100, 1000
        //testCounter(counter, 1000);
        //testCounter(counter1, 1000);
        testCounter(counter2, 1000);
        //testCounter(counter3, 1000);
    }

    @Test
    public void testLockCounter() {
        Counter counter = new LockCounter();

        testCounter(counter,100000);
    }

    @Test
    public void testMutexCounter() {
        Counter counter = new MutexCounter();

        testCounter(counter,100000);
    }

    @Test
    public void testConcurrentCounter() {
        Counter counter = new ConcurrentCounter();

        testCounter(counter,1000);
    }

    @Test
    public void testNaiveCounter()  {
        Counter counter = new SeqCounter();

        testCounter(counter, 1000);
    }

    private void testCounter(Counter counter, int iters) {
        Runnable increment = () -> {
            System.out.println(TwoThreadIds.me());
            for (int i = 0; i < iters; i++) {
                counter.increment();
            }
        };

        Thread t0 = new Thread(increment);
        Thread t1 = new Thread(increment);
        t0.start();
        t1.start();

        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long count = counter.getValue();
        System.out.println(count);
        assertEquals("Oops! Unexpected Behaviour!", iters * 2L, count);
    }
}
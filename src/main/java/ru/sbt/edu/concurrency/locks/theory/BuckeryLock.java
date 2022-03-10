package ru.sbt.edu.concurrency.locks.theory;

import ru.sbt.edu.concurrency.locks.ILock;
import ru.sbt.edu.concurrency.util.ThreadID;

public class BuckeryLock implements ILock {
    int[] label;
    boolean[] flag;

    public BuckeryLock(int n) {
        label = new int[n];
        flag = new boolean[n];
        for (int i = 0; i < n; i++) {
            label[i] = 0;
            flag[i] = false;
        }
    }

    @Override
    public void lock() {
        int i = ThreadID.get();
        flag[i] = true;
        int maxLabel = 0;


        maxLabel = getMaxLabel(maxLabel);
        label[i] = maxLabel + 1;

        for (int k = 0; k < flag.length; ++k) {
            if (k != i) {
                while (flag[k] && (label[k] < label[i] || (label[k] == label[i] && k < i))) {
                }
            }
        }
    }


    private int getMaxLabel(int maxTicket) {
        for (int i = 0; i < label.length; ++i) {
            int ticket = label[i];
            maxTicket = Math.max(ticket, maxTicket);
        }
        return maxTicket;
    }

    @Override
    public void unlock() {
        flag[ThreadID.get()] = false;
    }
}

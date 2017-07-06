package ru.apetrov;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by Andrey on 04.07.2017.
 */
@ThreadSafe
public class Count {

    @GuardedBy("Count")
    private int count;

    public Count() {
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public synchronized void increment() {
        this.count++;
    }
}

package jcipsettings;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class shows work with JCIP annotations
 *
 * @author Денис Висков
 * @version 1.0
 * @since 31.07.2020
 */
@ThreadSafe
public class Count {
    /**
     * Value
     */
    @GuardedBy("this")
    private int value;

    /**
     * Increment value
     */
    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}

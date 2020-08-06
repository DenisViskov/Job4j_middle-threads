package casoperations;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.08.2020
 */
@ThreadSafe
public class CasCount<T> {

    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int current;
        int newValue;
        do {
            current = count.get();
            newValue = ++current;
            count.set(newValue);
        } while (!count.compareAndSet(current, newValue));
    }

    public int get() {
        return count.get();
    }
}

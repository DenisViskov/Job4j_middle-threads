package casoperations;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Class is a CAS operations count
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.08.2020
 */
@ThreadSafe
public class CasCount<T> {

    /**
     * Count
     */
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    /**
     * Method execute increment count
     */
    public void increment() {
        int current;
        int newValue;
        do {
            current = count.get();
            newValue = current + 1;
        } while (!count.compareAndSet(current, newValue));
    }

    public int get() {
        return count.get();
    }
}

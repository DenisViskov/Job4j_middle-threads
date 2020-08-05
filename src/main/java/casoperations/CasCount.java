package casoperations;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Класс реализует ...
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.08.2020
 */
public class CasCount<T> {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int current;
        int newValue;
        do {
            current = count.get();
            newValue = ++current;
        } while (!count.compareAndSet(current, newValue));
    }

    public int get() {
        return count.get();
    }
}

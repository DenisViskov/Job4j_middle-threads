package threadsafedynamiclist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * Class is a SingleLockList
 *
 * @author Денис Висков
 * @version 1.0
 * @since 03.08.2020
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    /**
     * Container
     */
    @GuardedBy("this")
    private final DynamicsLinkedContainer<T> container = new DynamicsLinkedContainer<>();

    /**
     * Method add new value in list
     *
     * @param value
     */
    public synchronized void add(T value) {
        container.add(value);
    }

    /**
     * Method get element by given index
     *
     * @param index
     * @return T
     */
    public synchronized T get(int index) {
        return container.get(index);
    }

    /**
     * Method return Iterator
     *
     * @return Iterator
     */
    @Override
    public synchronized Iterator<T> iterator() {
        return copy();
    }

    /**
     * Method return copy of Iterator
     *
     * @return copy Iterator
     */
    private synchronized Iterator<T> copy() {
        DynamicsLinkedContainer<T> newContainer = new DynamicsLinkedContainer<>();
        container.forEach(newContainer::add);
        return newContainer.iterator();
    }
}

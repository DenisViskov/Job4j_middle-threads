package shabloneproducerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * Class is a SimpleBlockingQueue
 *
 * @author Денис Висков
 * @version 1.0
 * @since 04.08.2020
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    /**
     * Queue
     */
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    /**
     * Total count nodes
     */
    private final int total;

    public SimpleBlockingQueue(final int total) {
        this.total = total;
    }

    /**
     * Method add value in queue
     *
     * @param value
     */
    public synchronized void offer(T value) {
        if (queue.size() > total) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.offer(value);
        notifyAll();
    }

    /**
     * Method get value from queue
     *
     * @return T
     */
    public synchronized T poll() throws InterruptedException {
        Optional<T> box = Optional.ofNullable(queue.poll());
        if (!box.isPresent()) {
            try {
                wait();
                box = Optional.ofNullable(queue.poll());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        notifyAll();
        return box.get();
    }

    /**
     * Method of checking queue on empty
     *
     * @return boolean
     */
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

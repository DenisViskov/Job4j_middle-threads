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
        if (isTotal()) {
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
    public synchronized T poll() {
        Optional<T> box = Optional.ofNullable(queue.poll());
        while (!box.isPresent()) {
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
     * Method of checking total value in queue
     *
     * @return boolean
     */
    private synchronized boolean isTotal() {
        int count = 0;
        Queue<T> temp = new LinkedList<>();
        queue.forEach(elem -> temp.offer(elem));
        Iterator<T> it = temp.iterator();
        while (it.hasNext()) {
            it.next();
            count++;
        }
        return count >= total ? true : false;
    }
}

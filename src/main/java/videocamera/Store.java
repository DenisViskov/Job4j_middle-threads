package videocamera;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 25.08.2020
 */
@ThreadSafe
public class Store {
    /**
     * Queue from source URL
     */
    private final Queue<JSONObject> queue = new ConcurrentLinkedQueue<>();
    /**
     * Result queue for aggregate data
     */
    private final Queue<JSONObject> result = new ConcurrentLinkedQueue<>();

    /**
     * Method of add JSON to source queue
     *
     * @param object
     */
    public synchronized void add(JSONObject object) {
        queue.offer(object);
        notifyAll();
    }

    /**
     * Method return JSON from queue
     *
     * @return JSON
     */
    public synchronized JSONObject get() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        return queue.poll();
    }

    /**
     * Method of return boolean in dependency of value
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Method put JSON in result queue
     *
     * @param object
     */
    public void addToResult(JSONObject object) {
        result.offer(object);
    }

    public Queue<JSONObject> getResult() {
        return result;
    }
}

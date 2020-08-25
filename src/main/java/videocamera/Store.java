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
    private final Queue<JSONObject> queue = new ConcurrentLinkedQueue<>();
    private final Queue<JSONObject> result = new ConcurrentLinkedQueue<>();

    public synchronized void add(JSONObject object) {
        queue.offer(object);
        notifyAll();
    }

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

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void addToResult(JSONObject object) {
        result.offer(object);
    }

    public Queue<JSONObject> getResult() {
        return result;
    }
}

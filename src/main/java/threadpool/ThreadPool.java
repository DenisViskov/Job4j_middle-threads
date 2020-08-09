package threadpool;

import shabloneproducerconsumer.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс реализует ...
 *
 * @author Денис Висков
 * @version 1.0
 * @since 07.08.2020
 */
public class ThreadPool {
    private final List<Thread> threads;
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool() {
        this.tasks = new SimpleBlockingQueue<>(10);
        this.threads = new ArrayList<>(Runtime.getRuntime().availableProcessors());
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {

    }

    public void execute() {

    }

}

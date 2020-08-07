package threadpool;

import shabloneproducerconsumer.SimpleBlockingQueue;

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

    public ThreadPool(List<Thread> threads, SimpleBlockingQueue<Runnable> tasks) {
        this.threads = checkThreadsOnAmountProcessors(threads);
        this.tasks = tasks;
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {

    }

    private List<Thread> checkThreadsOnAmountProcessors(List<Thread> threads) {
        if (threads.size() > Runtime.getRuntime().availableProcessors()) {
            throw new IllegalArgumentException("Count of threads more than amount of processors");
        }
        return threads;
    }
}

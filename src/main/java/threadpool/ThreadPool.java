package threadpool;

import shabloneproducerconsumer.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class is a Thread Pool
 *
 * @author Денис Висков
 * @version 1.0
 * @since 07.08.2020
 */
public class ThreadPool {
    /**
     * Threads
     */
    private final List<Thread> threads;

    /**
     * Tasks
     */
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool() {
        this.tasks = new SimpleBlockingQueue<>(10);
        this.threads = new ArrayList<>();
        createExecutors();
    }

    /**
     * Method add task in tasks list
     *
     * @param job
     */
    public void work(Runnable job) {
        tasks.offer(job);
    }

    /**
     * Method stops all threads
     */
    public void shutdown() {
        if (tasks.isEmpty()) {
            threads.forEach(thread -> {
                thread.interrupt();
            });
        }
    }

    /**
     * Method of run all threads
     */
    public void execute() {
        threads.forEach(thread -> thread.start());
    }

    /**
     * Method create and add new thread in threads list
     */
    private void createExecutors() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            threads.add(new Executor(tasks));
        }
    }

}

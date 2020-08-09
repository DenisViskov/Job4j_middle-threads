package threadpool;

import shabloneproducerconsumer.SimpleBlockingQueue;

/**
 * Класс реализует ...
 *
 * @author Денис Висков
 * @version 1.0
 * @since 09.08.2020
 */
public class Executor extends Thread {
    private final SimpleBlockingQueue<Runnable> tasks;

    public Executor(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                tasks.poll().run();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

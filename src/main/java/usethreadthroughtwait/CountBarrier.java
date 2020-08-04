package usethreadthroughtwait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class is a count barrier
 *
 * @author Денис Висков
 * @version 1.0
 * @since 03.08.2020
 */
@ThreadSafe
public class CountBarrier {
    /**
     * This
     */
    private final Object monitor = this;

    /**
     * Max count
     */
    private final int total;

    /**
     * Count
     */
    @GuardedBy("monitor")
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    /**
     * Method of increment
     */
    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    /**
     * Method of thread sleeping
     */
    public void await() {
        synchronized (monitor) {
            while (count != total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountBarrier barrier = new CountBarrier(3);
        Thread first = new Thread(
                () -> {
                    barrier.await();
                    System.out.println("first finished");
                }
        );
        Thread second = new Thread(
                () -> {
                    barrier.await();
                    System.out.println("second finished");
                }
        );
        first.start();
        second.start();
        Thread.sleep(5000);
        barrier.count();
        barrier.count();
        barrier.count();
        first.join();
        second.join();
    }
}

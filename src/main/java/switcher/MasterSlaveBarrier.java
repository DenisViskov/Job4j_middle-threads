package switcher;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class is a Master Slave Barrier
 *
 * @author Денис Висков
 * @version 1.0
 * @since 16.08.2020
 */
@ThreadSafe
public class MasterSlaveBarrier {

    /**
     * Current thread
     */
    @GuardedBy("this")
    private Thread current;

    /**
     * Method of run master
     */
    public synchronized void tryMaster() {
        setCurrent(Thread.currentThread());
        while (!current.isInterrupted()) {
            System.out.println("Thread A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        doneMaster();
    }

    /**
     * Method of run slave
     */
    public synchronized void trySlave() {
        if (current == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        setCurrent(Thread.currentThread());
        while (!current.isInterrupted()) {
            System.out.println("Thread B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        doneSlave();
    }

    /**
     * Method of done master
     * sends notifyAll()
     */
    public void doneMaster() {
        notifyAll();
    }

    /**
     * Method of done slave
     * interrupt current thread
     */
    public void doneSlave() {
        current.interrupt();
    }

    public Thread getCurrent() {
        return current;
    }

    public void setCurrent(Thread current) {
        this.current = current;
    }
}

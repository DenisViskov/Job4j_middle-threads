package switcher;

/**
 * Class is a Master Slave Barrier
 *
 * @author Денис Висков
 * @version 1.0
 * @since 16.08.2020
 */
public class MasterSlaveBarrier {

    /**
     * Method of run master
     */
    public void tryMaster() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Method of run slave
     */
    public void trySlave() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
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
        Thread.currentThread().interrupt();
    }
}

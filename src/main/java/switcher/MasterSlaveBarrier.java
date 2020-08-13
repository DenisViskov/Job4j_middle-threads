package switcher;

/**
 * Класс реализует ...
 *
 * @author Денис Висков
 * @version 1.0
 * @since 13.08.2020
 */
public class MasterSlaveBarrier {

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

    public void trySlave() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void doneMaster() {
        Thread.currentThread().interrupt();
    }

    public void doneSlave() {
        Thread.currentThread().interrupt();
    }
}

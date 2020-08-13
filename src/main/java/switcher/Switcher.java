package switcher;

/**
 * Класс реализует ...
 *
 * @author Денис Висков
 * @version 1.0
 * @since 13.08.2020
 */
public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {
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
        );
        Thread second = new Thread(
                () -> {
                    while (!first.isInterrupted()) {
                        try {
                            Thread.currentThread().wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread B");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        first.start();
        second.start();
        Thread.sleep(5000);
        first.interrupt();
        Thread.sleep(10000);
        second.interrupt();
        first.join();
        second.join();
    }
}

package interruptthread;

/**
 * Class has example loading in another thread
 *
 * @author Денис Висков
 * @version 1.0
 * @since 29.07.2020
 */
public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                System.out.print("\r load: " + "\\");
                Thread.sleep(500);
                System.out.print("\r load: " + "|");
                Thread.sleep(500);
                System.out.print("\r load: " + "/");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1500);
        progress.interrupt();
    }
}

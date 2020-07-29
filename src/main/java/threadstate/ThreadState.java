package threadstate;

/**
 * Class has example of waiting main thread
 *
 * @author Денис Висков
 * @version 1.0
 * @since 29.07.2020
 */
public class ThreadState {

    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                && second.getState() != Thread.State.TERMINATED) {

        }
        System.out.println("work is done");
    }
}

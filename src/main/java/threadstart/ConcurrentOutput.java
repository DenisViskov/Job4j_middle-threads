package threadstart;

/**
 * Class has realizes example of working threads
 *
 * @author Денис Висков
 * @version 1.0
 * @since 29.07.2020
 */
public class ConcurrentOutput {

    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}

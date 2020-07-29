package standbymode;

/**
 * Class shows loading in thread
 *
 * @author Денис Висков
 * @version 1.0
 * @since 29.07.2020
 */
public class Wget {

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        int index = 0;
                        while (index < 101) {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + index + "%");
                            index++;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }
}

package modelofmemoryjava;

/**
 * Class has example correct synchronized singleton in multithreading place
 *
 * @author Денис Висков
 * @version 1.0
 * @since 30.07.2020
 */
public class DCLSingleton {
    /**
     * Singleton
     */
    private volatile static DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }
}

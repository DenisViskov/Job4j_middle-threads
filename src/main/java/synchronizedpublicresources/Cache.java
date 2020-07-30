package synchronizedpublicresources;

/**
 * Class has example synchronized method
 *
 * @author Денис Висков
 * @version 1.0
 * @since 30.07.2020
 */
public final class Cache {
    /**
     * Cache
     */
    private static Cache cache;

    /**
     * Singleton synchronized
     *
     * @return Cache
     */
    public synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}

package nonblockingcache;

/**
 * Interface of Cache
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.08.2020
 */
public interface Cache<T> {

    /**
     * Method should add some in cache
     *
     * @param model
     * @return boolean
     */
    boolean add(T model);

    /**
     * Method should update given model in cache
     *
     * @param model
     */
    void update(T model);

    /**
     * Method should delete model from cache
     *
     * @param model
     * @return boolean
     */
    boolean delete(T model);
}

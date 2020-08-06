package nonblockingcache;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.08.2020
 */
public interface Cache<T> {
    boolean add(T model);

    boolean update(T model);

    boolean delete(T model);
}

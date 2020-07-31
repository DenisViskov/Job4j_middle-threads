package classuserstorage;

/**
 * Interface of Storage
 *
 * @author Денис Висков
 * @version 1.0
 * @since 31.07.2020
 */
public interface Storage<T> {

    /**
     * Method should add something to storage
     *
     * @param some
     * @return boolean
     */
    boolean add(T some);

    /**
     * Method should update something in storage
     *
     * @param some
     * @return boolean
     */
    boolean update(T some);

    /**
     * Method should delete something from storage
     *
     * @param some
     * @return boolean
     */
    boolean delete(T some);

    /**
     * Method should transfer something from id to new id on amount
     *
     * @param fromID
     * @param toID
     * @param amount
     */
    void transfer(int fromID, int toID, int amount);
}

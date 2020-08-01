package classuserstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

/**
 * Class of User Storage
 *
 * @author Денис Висков
 * @version 1.0
 * @since 31.07.2020
 */
@ThreadSafe
public class UserStorage implements Storage<User> {

    @GuardedBy("this")
    /**
     * Store
     */
    private Map<Integer, User> store;

    public UserStorage() {
        store = new HashMap<>();
    }

    /**
     * Method add to store some user
     *
     * @param some
     * @return boolean
     */
    @Override
    public synchronized boolean add(User some) {
        boolean result = false;
        if (!store.containsKey(some.getId())) {
            store.put(some.getId(), some);
            result = true;
        }
        return result;
    }

    /**
     * Method update in store some user
     *
     * @param some
     * @return boolean
     */
    @Override
    public synchronized boolean update(User some) {
        boolean result = false;
        if (store.containsKey(some.getId())) {
            store.put(some.getId(), some);
            result = true;
        }
        return result;
    }

    /**
     * Method delete from store some user
     *
     * @param some
     * @return boolean
     */
    @Override
    public synchronized boolean delete(User some) {
        boolean result = false;
        if (store.containsKey(some.getId())) {
            store.remove(some.getId());
            result = true;
        }
        return result;
    }

    /**
     * Method execute transfer money from first ID to second ID
     *
     * @param fromID
     * @param toID
     * @param amount
     */
    @Override
    public synchronized void transfer(int fromID, int toID, int amount) {
        if (store.containsKey(fromID) && store.containsKey(toID)) {
            User first = store.get(fromID);
            User second = store.get(toID);
            first.setAmount(first.getAmount() - amount);
            second.setAmount(second.getAmount() + amount);
        }
    }
}

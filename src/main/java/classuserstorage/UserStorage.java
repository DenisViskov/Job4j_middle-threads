package classuserstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private List<User> store;

    public UserStorage() {
        store = new ArrayList<>();
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
        if (!store.contains(some)) {
            store.add(some);
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
        Optional<User> box = Optional.ofNullable(findByID(some.getId()));
        if (box.isPresent()) {
            store.remove(box.get());
            store.add(some);
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
        if (store.contains(some)) {
            store.remove(some);
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
        Optional<User> from = Optional.ofNullable(findByID(fromID));
        Optional<User> to = Optional.ofNullable(findByID(toID));
        if (from.isPresent() && to.isPresent()) {
            User first = from.get();
            User second = to.get();
            first.setAmount(first.getAmount() - amount);
            second.setAmount(second.getAmount() + amount);
        }
    }

    /**
     * Method return User by given ID
     *
     * @param id
     * @return User or Null in dependency of result
     */
    public synchronized User findByID(int id) {
        return store.stream()
                .filter(user -> user.getId() == id)
                .findFirst().get();
    }
}

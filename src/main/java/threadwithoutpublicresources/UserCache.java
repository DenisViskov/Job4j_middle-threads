package threadwithoutpublicresources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Class is an user cache
 *
 * @author Денис Висков
 * @version 1.0
 * @since 31.07.2020
 */
public class UserCache {
    /**
     * Users map
     */
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    /**
     * ID
     */
    private final AtomicInteger id = new AtomicInteger();

    /**
     * Method add user in map
     *
     * @param user
     */
    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    /**
     * Method return user by ID
     *
     * @param id
     * @return User
     */
    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    /**
     * Method returns all users
     *
     * @return List
     */
    public List<User> findAll() {
        return users.values().stream()
                .map(user -> User.of(user.getName()))
                .collect(Collectors.toList());
    }
}

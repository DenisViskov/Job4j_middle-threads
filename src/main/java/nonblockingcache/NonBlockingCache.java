package nonblockingcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.08.2020
 */
public class NonBlockingCache implements Cache<Base> {

    private Map<Integer, Base> cache;

    public NonBlockingCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    @Override
    public boolean add(Base model) {
        boolean result = false;
        if (!cache.containsKey(model.getId())) {
            cache.put(model.getId(), model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean update(Base model) {
        return false;
    }

    @Override
    public boolean delete(Base model) {
        boolean result = false;
        if (cache.containsKey(model.getId())) {
            cache.remove(model.getId());
            result = true;
        }
        return result;
    }
}

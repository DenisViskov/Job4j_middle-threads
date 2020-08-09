package nonblockingcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class is a NonBlockingCache
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.08.2020
 */
public class NonBlockingCache implements Cache<Base> {

    /**
     * Cache
     */
    private final Map<Integer, Base> cache;

    public NonBlockingCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    /**
     * Method add model in cache
     *
     * @param model
     * @return boolean
     */
    @Override
    public boolean add(Base model) {
        boolean result = false;
        if (!cache.containsKey(model.getId())) {
            cache.put(model.getId(), model);
            result = true;
        }
        return result;
    }

    /**
     * Method update model in cache
     *
     * @param model
     */
    @Override
    public void update(Base model) {
        model.getTmpVersion().set(model.getVersion());
        cache.computeIfPresent(model.getId(), (key, value) -> {
            int current = model.getTmpVersion().get();
            int newVersion = current + 1;
            if (!model.getTmpVersion().compareAndSet(current, newVersion)) {
                throw new OptimisticException();
            }
            model.setVersion(model.getTmpVersion().get());
            return model;
        });
    }

    /**
     * Method delete model from cache
     *
     * @param model
     * @return boolean
     */
    @Override
    public boolean delete(Base model) {
        boolean result = false;
        if (cache.containsKey(model.getId())) {
            cache.remove(model.getId());
            result = true;
        }
        return result;
    }

    public Map<Integer, Base> getCache() {
        return cache;
    }
}

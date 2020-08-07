package nonblockingcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.08.2020
 */
public class NonBlockingCache implements Cache<Base> {

    private final Map<Integer, Base> cache;
    private AtomicReference<Integer> version;

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
    public void update(Base model) {
        version.set(model.getVersion());
        cache.computeIfPresent(model.getId(), (key, value) -> {
            int current = version.get();
            int newVersion = current + 1;
            if (!version.compareAndSet(current, newVersion)) {
                throw new OptimisticException();
            }
            model.setVersion(version.get());
            return model;
        });
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

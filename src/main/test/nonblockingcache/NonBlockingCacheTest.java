package nonblockingcache;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class NonBlockingCacheTest {

    @Test
    public void addTest() throws InterruptedException {
        Base base = new Base(1, "asd");
        NonBlockingCache cache = new NonBlockingCache();
        Thread first = new Thread(() -> {
            cache.add(base);
        });
        Thread second = new Thread(() -> {
            cache.add(base);
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(cache.getCache().size(), is(1));
    }

    @Test
    public void updateTest() throws InterruptedException {
        Base base = new Base(1, "asd");
        NonBlockingCache cache = new NonBlockingCache();
        Thread first = new Thread(() -> {
            cache.add(base);
        });
        Thread second = new Thread(() -> {
            cache.update(base);
        });
        first.start();
        second.start();
        first.join();
        second.join();
        int expected = base.getVersion();
        assertThat(expected, is(1));
    }

    @Test
    public void deleteTest() throws InterruptedException {
        Base base = new Base(1, "asd");
        Base some = new Base(2, "asd");
        NonBlockingCache cache = new NonBlockingCache();
        Thread first = new Thread(() -> {
            cache.add(base);
        });
        Thread second = new Thread(() -> {
            cache.add(some);
            cache.delete(base);
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(cache.getCache().size(), is(1));
    }
}
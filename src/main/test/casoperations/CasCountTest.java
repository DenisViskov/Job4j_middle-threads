package casoperations;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class CasCountTest {

    @Test
    public void incrementTest() throws InterruptedException {
        CasCount<Integer> casCount = new CasCount<>();
        Thread first = new Thread(() -> {
            casCount.increment();
        });
        Thread second = new Thread(() -> {
            casCount.increment();
        });
        first.start();
        //second.start();
        first.join();
        //second.join();
        int out = casCount.get();
        assertThat(out, is(2));
    }
}
package shabloneproducerconsumer;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {

    @Test
    public void simpleBlockingTest() throws InterruptedException {
        boolean result = false;
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            queue.offer(1);
            queue.offer(2);
            queue.offer(3);
            queue.offer(4);
            queue.offer(5);
            queue.offer(6);
        });
        Thread consumer = new Thread(() -> {
            queue.poll();
            queue.poll();
            queue.poll();
        });
        consumer.start();
        Thread.sleep(5000);
        producer.start();
        producer.join();
        consumer.join();
        result = true;
        assertThat(result, is(true));
    }
}
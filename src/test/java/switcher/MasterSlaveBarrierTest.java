package switcher;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class MasterSlaveBarrierTest {

    @Test
    public void tryMasterTest() throws InterruptedException {
        boolean result = false;
        MasterSlaveBarrier barrier = new MasterSlaveBarrier();
        Thread first = new Thread(() -> {
            synchronized (barrier) {
                barrier.tryMaster();
                barrier.doneMaster();
            }
        });
        Thread second = new Thread(() -> {
            synchronized (barrier) {
                try {
                    barrier.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                barrier.trySlave();
            }
        });
        second.start();
        first.start();
        Thread.sleep(5000);
        first.interrupt();
        first.join();
        Thread.sleep(5000);
        second.interrupt();
        second.join();
        result = true;
        assertThat(result, is(true));
    }
}
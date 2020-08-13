package switcher;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class MasterSlaveBarrierTest {

    @Test
    public void tryMasterTest() throws InterruptedException {
        MasterSlaveBarrier barrier = new MasterSlaveBarrier();
        Thread first = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                barrier.tryMaster();
                notifyAll();
            }
        });
        Thread second = new Thread(() -> {
            while (!first.isInterrupted()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            barrier.trySlave();
        });
        first.start();
        second.start();
        Thread.sleep(10000);
        first.interrupt();
        first.join();
        second.join();
    }
}
package switcher;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class MasterSlaveBarrierTest {

    @Test
    public void tryMasterTest() throws InterruptedException {
        MasterSlaveBarrier barrier = new MasterSlaveBarrier();
        Thread first = new Thread(barrier::tryMaster);
        Thread second = new Thread(barrier::trySlave);
        second.start();
        first.start();
        Thread.sleep(5000);
        barrier.doneMaster();
        first.join();
        Thread.sleep(5000);
        barrier.doneSlave();
        second.join();
        assertThat(barrier.isMaster(), is(false));
        assertThat(barrier.isSlave(), is(false));
    }
}
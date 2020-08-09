package threadpool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shabloneproducerconsumer.SimpleBlockingQueue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class ThreadPoolTest {

    private final ThreadPool pool = new ThreadPool();
    private final PrintStream console = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream out = new PrintStream(outputStream);

    @Before
    public void setUp() {
        for (int i = 0; i < 10; i++) {
            pool.work(() -> System.out.println("Hi All"));
        }
        System.setOut(out);
    }

    @Test
    public void executeTest() throws InterruptedException {
        pool.execute();
        Thread.sleep(2000);
        pool.shutdown();
        assertThat(outputStream.toString(), is("Hi All" + System.lineSeparator()
                + "Hi All" + System.lineSeparator()
                + "Hi All" + System.lineSeparator()
                + "Hi All" + System.lineSeparator()
                + "Hi All" + System.lineSeparator()
                + "Hi All" + System.lineSeparator()
                + "Hi All" + System.lineSeparator()
                + "Hi All" + System.lineSeparator()
                + "Hi All" + System.lineSeparator()
                + "Hi All" + System.lineSeparator()));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(console);
    }
}
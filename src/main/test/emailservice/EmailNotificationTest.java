package emailservice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.*;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class EmailNotificationTest {

    private final PrintStream console = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream out = new PrintStream(outputStream);

    @Before
    public void setUp() throws Exception {
        System.setOut(out);
    }

    @Test
    public void emailToTest() throws InterruptedException {
        Email email = new EmailNotification();
        User ivan = new User("Ivan", "ivan@mail.ru");
        User pavel = new User("Pavel", "pavel@mail.ru");
        Thread first = new Thread(() -> {
            email.emailTo(ivan);
        });
        Thread second = new Thread(() -> {
            email.emailTo(pavel);
        });
        first.start();
        second.start();
        first.join();
        second.join();
        boolean result = email.close();
        String[] splitOut = outputStream.toString().split(System.lineSeparator());
        assertThat(splitOut, arrayContainingInAnyOrder("Add a new event to Pavel", "Add a new event to Ivan"));
        assertThat(result, is(true));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(console);
    }
}
package emailservice;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import threadpool.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class is an notification service
 *
 * @author Денис Висков
 * @version 1.0
 * @since 10.08.2020
 */
public class EmailNotification implements Email<User> {

    /**
     * Thread pool service
     */
    private final ExecutorService service = Executors.newFixedThreadPool(Runtime
            .getRuntime()
            .availableProcessors());

    /**
     * Method send mail to given User
     *
     * @param to
     */
    @Override
    public void emailTo(User to) {
        service.submit(() -> {
            String subject = "Notification" + " "
                    + to.getUserName() + " "
                    + "to email" + " "
                    + to.getEmail();
            String body = "Add a new event to" + " " + to.getUserName();
            send(subject, body, to.getEmail());
        });
    }

    /**
     * Method of close thread pool
     *
     * @return boolean
     */
    @Override
    public boolean close() {
        service.shutdown();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return service.isTerminated();
    }

    /**
     * Method print message on console
     *
     * @param subject
     * @param body
     * @param email
     */
    @Override
    public void send(String subject, String body, String email) {
        System.out.println(body);
    }
}

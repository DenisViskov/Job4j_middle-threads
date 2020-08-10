package emailservice;

/**
 * Interface of Email<T>
 *
 * @author Денис Висков
 * @version 1.0
 * @since 10.08.2020
 */
public interface Email<T> {

    /**
     * Method should send mail to given User
     *
     * @param to
     */
    void emailTo(T to);

    /**
     * Method should close thread pool
     *
     * @return boolean
     */
    boolean close();

    /**
     * Method should send message
     *
     * @param subject
     * @param body
     * @param email
     */
    void send(String subject, String body, String email);
}

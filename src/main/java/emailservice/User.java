package emailservice;

/**
 * Class is an User
 * @author Денис Висков
 * @version 1.0
 * @since 10.08.2020
 */
public class User {
    /**
     * User name
     */
    private final String userName;

    /**
     * Email
     */
    private final String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}

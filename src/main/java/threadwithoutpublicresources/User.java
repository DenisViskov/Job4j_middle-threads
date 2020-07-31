package threadwithoutpublicresources;

/**
 * Class model data of user
 *
 * @author Денис Висков
 * @version 1.0
 * @since 31.07.2020
 */
public class User {
    /**
     * Id
     */
    private int id;

    /**
     * Name
     */
    private String name;

    public static User of(String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

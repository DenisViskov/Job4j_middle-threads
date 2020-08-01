package classuserstorage;

import java.util.Objects;

/**
 * Class of data user
 *
 * @author Денис Висков
 * @version 1.0
 * @since 31.07.2020
 */
public class User {
    /**
     * ID
     */
    private final int id;

    /**
     * Amount
     */
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return getId() == user.getId()
                && getAmount() == user.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAmount());
    }
}

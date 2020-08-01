package classuserstorage;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class UserStorageTest {

    @Test
    public void addTest() {
        UserStorage storage = new UserStorage();
        boolean result = storage.add(new User(1, 200));
        assertThat(true, is(result));
    }

    @Test
    public void updateTest() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 200);
        storage.add(user);
        boolean result = storage.update(new User(1, 500));
        assertThat(true, is(result));
    }

    @Test
    public void deleteTest() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 200);
        storage.add(user);
        boolean result = storage.delete(user);
        assertThat(true, is(result));
    }

    @Test
    public void transferTest() {
        UserStorage storage = new UserStorage();
        User first = new User(1, 200);
        User second = new User(2, 500);
        storage.add(first);
        storage.add(second);
        storage.transfer(1, 2, 100);
        assertThat(first.getAmount(), is(100));
        assertThat(second.getAmount(), is(600));
    }
}
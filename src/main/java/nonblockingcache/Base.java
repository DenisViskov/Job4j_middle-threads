package nonblockingcache;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class is a Base
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.08.2020
 */
public class Base {
    /**
     * ID
     */
    private final int id;

    /**
     * Version
     */
    private int version;

    /**
     * Name
     */
    private String name;

    /**
     * Temp version
     */
    private final AtomicReference<Integer> tmpVersion;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 0;
        this.tmpVersion = new AtomicReference<>(0);
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AtomicReference<Integer> getTmpVersion() {
        return tmpVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id
                && version == base.version
                && Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name);
    }
}

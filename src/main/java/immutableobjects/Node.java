package immutableobjects;



/**
 * Class has example immutable class
 *
 * @author Денис Висков
 * @version 1.0
 * @since 30.07.2020
 */
public class Node<T> {
    /**
     * Next Node
     */
    private final Node next;

    /**
     * Value
     */
    private final T value;

    public Node(Node next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}


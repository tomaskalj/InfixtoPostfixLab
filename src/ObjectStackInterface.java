/**
 * Interface containing to methods used to implement
 * a Stack to store variables of any data type.
 *
 * @author Richard Stegman
 */
public interface ObjectStackInterface {
    boolean isEmpty();

    boolean isFull();

    void clear();

    /**
     * Pushes an object to the top of the stack.
     * @param o the object being pushed
     */
    void push(Object o);

    Object pop();

    Object top();
}

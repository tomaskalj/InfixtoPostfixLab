/**
 * Implementation of the {@link ObjectStackInterface} to
 * perform stack operations using any data type.
 *
 * @author Richard Stegman
 */
public class ObjectStack implements ObjectStackInterface {
    private Object[] item;
    private int top;

    public ObjectStack() {
        item = new Object[1];
        top = -1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == item.length - 1;
    }

    @Override
    public void clear() {
        item = new Object[1];
        top = -1;
    }

    /**
     * Pushes an object to the top of the stack.
     * @param o the object being pushed
     */
    @Override
    public void push(Object o) {
        if (isFull()) {
            resize(2 * item.length);
        }
        item[++top] = o;
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            System.exit(1);
        }
        Object temp = item[top];
        item[top--] = null;
        if (top == item.length / 4) {
            resize(item.length / 2);
        }
        return temp;
    }

    @Override
    public Object top() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            System.exit(1);
        }
        return item[top];
    }

    /**
     * Resizes the stack to accommodate for more objects.
     * @param size the desired size for the stack
     */
    private void resize(int size) {
        Object[] temp = new Object[size];
        for (int i = 0; i <= top; i++) {
            temp[i] = item[i];
        }
        item = temp;
    }
}

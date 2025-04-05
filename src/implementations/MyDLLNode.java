package implementations;

/**
 * This class represents a single node to be used in a doubly linked list
 * @author Mace Howald
 * @version 1.0
 */

public class MyDLLNode<E> {
    E data;
    MyDLLNode<E> prev;
    MyDLLNode<E> next;

    public MyDLLNode(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
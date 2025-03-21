import java.util.Iterator;
import java.util.EmptyStackException;
/**
 * An interface for the Stack Abstract Data Type (ADT).
 *
 * @param <E> the elements in this stack
 * @author Ethan Van De Woestyne
 */
public interface StackADT<E> {
    
    /**
     * Pushes an element onto the top of this stack.
     * 
     * @param element the element to be pushed onto this stack
     * @throws NullPointerException if the specified element is null and this
     *         stack does not permit null elements
     * @throws IllegalStateException if the stack is fixed size and is full
     * @post The stack will contain the specified element at the top
     */
    void push(E element);
    
    /**
     * Removes and returns the element at the top of this stack.
     * 
     * @return the element at the top of this stack
     * @throws EmptyStackException if this stack is empty
     * @post The element at the top of the stack is removed
     */
    E pop();
    
    /**
     * Returns, but does not remove, the element at the top of this stack.
     * 
     * @return the element at the top of this stack
     * @throws EmptyStackException if this stack is empty
     * @post The stack remains unchanged
     */
    E peek();
    
    /**
     * Compares this stack with another stack for equality.
     * Two stacks are considered equal if they contain the same elements
     * in the same order.
     * 
     * @param that the stack to be compared for equality with this stack
     * @return true if the stacks are equal, false otherwise
     * @throws NullPointerException if the specified stack is null
     * @pre Both stacks must have implementations of the equals method
     * @post The stack remains unchanged
     */
    boolean equals(StackADT<E> that);
	
	/**
     * Returns an iterator over the elements in this stack in proper sequence
     * (from top to bottom element).
     *
     * @return an iterator over the elements in this stack in proper sequence
     * @post The stack remains unchanged unless the iterator's remove method is called
     */
	Iterator<E> iterator();
    
    /**
     * Returns an array containing all of the elements in this stack in proper sequence
     * (from top to bottom element).
     * 
     * @return an array containing all of the elements in this stack in proper sequence
     * @post The stack remains unchanged
     */
    Object[] toArray();
	
	/**
     * Returns the 1-based position where an element is on this stack.
     * If the element occurs multiple times on the stack, the position closest
     * to the top is returned.
     * 
     * @param element the element to search for
     * @return the 1-based position from the top of the stack where the element is located;
     *         -1 if the element is not found
     * @throws NullPointerException if the specified element is null and this
     *         stack does not permit null elements
     * @post The stack remains unchanged
     */
    int search(E element);
	
	/**
     * Returns the number of elements in this stack.
     * 
     * @return the number of elements in this stack
     * @post The stack remains unchanged
     */
    int size();
	
	E[] toArray(E[] copy);
	
    /**
     * Returns true if this stack contains the specified element.
     * 
     * @param obj element whose presence in this stack is to be tested
     * @return true if this stack contains the specified element, false otherwise
     * @throws NullPointerException if the specified element is null and this
     *         stack does not permit null elements
     * @post The stack remains unchanged
     */
	boolean contains(E obj);
	
    /**
     * Returns true if this stack contains no elements.
     * 
     * @return true if this stack contains no elements, false otherwise
     * @post The stack remains unchanged
     */
	boolean isEmpty();
	
    /**
     * Removes all elements from this stack.
     * The stack will be empty after this method returns.
     * 
     * @return true if the operation was successful, false otherwise
     * @post The stack is empty
     */
	boolean clear();
}
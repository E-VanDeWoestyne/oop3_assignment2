package utilities;
/**
 * An interface for the Queue Abstract Data Type (ADT).
 *
 * @param <E> the elements in this queue
 * @author Mace Howald
 */
public interface QueueADT<E> {
	
	/**
	 * Adds an element to the end of the queue
	 * 
	 * @param element, the element to be added
	 * @throws IllegalArgumentException if element is null
	 * @post The element has been added to the end of the queue
	 */
	void enqueue(E element);
	
	/**
	 * Removes and returns the element at the front of the queue
	 * 
	 * @pre The queue is not empty
	 * @throws EmptyQueueException if queue is empty
	 * @return The element that was at the front of the queue.
	 * @post The element at the front of the queue has been removed.
	 */
	E dequeue();
	
    /**
     * Returns the element at the front of the queue without removing it
     *
     * @pre The queue is not empty.
     * @return The element at the front of the queue
     * @throws EmptyQueueException if the queue is empty
	 * @post The queue remains unchanged
     */
	E peek();
	
	/**
	 * Returns true if queue is empty, false if not
	 * 
	 * @return True if the queue is empty, false if not
	 * @post The queue remains unchanged
	 */
	boolean isEmpty();
	
	/**
	 * Returns number of elements currently in the queue
	 * 
	 * @return the number of elements in the queue
	 * @post The queue remains unchanged
	 */
	int size();
	
	/**
	 * Removes all elements from the queue
	 * 
	 * @post The queue is empty
	 */
	void dequeueAll();
	
	/**
	 * Compares this queue to another to see if they
	 * contain the same elements in the same order.
	 * 
	 * @param other, the other queue to compare
	 * @return true if the queues are equal, false if not
	 * @post The queue remains unchanged
	 */
	boolean equals(QueueADT<?> other);
	
	/**
     * Returns an iterator over the elements in this queue
	 * in proper sequence from front to back.
     * 
     * @return an iterator over the elements in this queue
	 * @post The queue remains unchanged
     */
	utilities.Iterator<E> iterator();
	
	/**
     * Returns an array containing all of the elements in this queue 
	 * in proper front to back sequence
     * The runtime type of the returned array is that of the specified array.
     * 
     * @param copy the array into which the elements are to be stored, if it is large enough,
     *             otherwise, a new array of the same runtime type is allocated
     * @return an array containing all of the elements in this queue
     * @throws NullPointerException if the specified array is null
     * @post The queue remains unchanged
     */
	E[] toArray(E[] copy);
	
	/**
	 * Returns true if the queue is full, false if not
	 * 
	 * @return true if the queue is full, false if not
	 * @post The queue remains unchanged
	 */
	boolean isFull();
	
	/**
	 * Returns true if queue contains specified element
	 * 
	 * @param obj, element to check the queue for
	 * @return true if queue contains specified element
	 * @throws NullPointerException if the specified element is null
	 * @post The queue remains unchanged
	 */
	boolean contains(E obj);
	
	/**
     * Returns the 1-based position where an element is in this queue.
     * If the element appears multiple times, the position closest to the front is returned.
     * 
     * @param element the element to search for
     * @return the 1-based position from the front of the queue where the element is located,
     *         -1 if the element is not found
     * @throws NullPointerException if the specified element is null
     * @post The queue remains unchanged
     */
	int search(E element);
}

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
     */
	E peek();
	
	/**
	 * Returns true if queue is empty, false if not
	 * 
	 * @return True if the queue is empty, false if not
	 */
	boolean isEmpty();
	
	/**
	 * Returns number of elements currently in the queue
	 * 
	 * @return the number of elements in the queue
	 */
	int size();
	
	/**
	 * Removes all elements from the queue
	 * 
	 * @post the queue is empty
	 */
	void dequeueAll();
	
	/**
	 * Compares this queue to another to see if they
	 * contain the same elements in the same order.
	 * 
	 * @param other, the other queue to compare
	 * @return true if the queues are equal, false if not
	 */
	boolean equals(QueueADT<?> other);
	
	/**
	 * 
	 * @return
	 */
	utilities.Iterator<E> iterator();
	
	/**
	 * 
	 * @param copy
	 * @return
	 */
	E[] toArray(E[] copy);
	
	/**
	 * Returns true if the queue is full, false if not
	 * 
	 * @return true if the queue is full, false if not
	 */
	boolean isFull();
	
	/**
	 * Returns true if queue contains specified element
	 * 
	 * @param obj, element to check the queue for
	 * @return true if queue contains specified element
	 * @throws NullPointerException if the specified element is null
	 */
	boolean contains(E obj);
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	int search(E element);
}

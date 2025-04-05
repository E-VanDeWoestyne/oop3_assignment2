package implementations;

import utilities.QueueADT;
import utilities.Iterator;
import exceptions.EmptyQueueException;


/**
 * This class represents a Queue meant to be used by XMLParser
 * @author Mace Howald
 * @version 1.0
 */

public class MyQueue<E> implements QueueADT<E> {

	private static final long serialVersionUID = 1L;
	private MyDLL<E> queue;
	
	public MyQueue() {
		queue = new MyDLL<>();
	}
	
	
	@Override
	public void enqueue(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot queue null.");
		}
		queue.add(toAdd);
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Queue is empty.");
		}
		return queue.remove(0);
	}

	@Override
	public E peek() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Queue is empty.");
		}
		return queue.get(0);
	}

	@Override
	public void dequeueAll() {
		queue.clear();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		return queue.contains(toFind);
	}

	@Override
	public int search(E toFind) {
		if (toFind == null) {
			return -1; // Fail search if nothing to find
		}
		Iterator<E> it = queue.iterator();
		int pos = 1;
		while (it.hasNext()) {
			E current = it.next();
			if (toFind.equals(current)) {
				return pos; // Return found position
			}
			pos ++;
		}
		return -1; // Fail search if nothing found
	}

	@Override
	public Iterator<E> iterator() {
		return queue.iterator();
	}

	@Override
	public boolean equals(QueueADT<E> that) {
		
		Iterator<E> thisIt = this.iterator();
        Iterator<E> thatIt = that.iterator();
        
        while (thisIt.hasNext()) {
            if (!thisIt.next().equals(thatIt.next())) {
                return false;
            }
        }
        return true;
	}

	@Override
	public Object[] toArray() {
		return queue.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return queue.toArray(holder);
	}

	@Override
	public boolean isFull() {
		return false; // Should be false as a DLL doesn't have a set size
	}

	@Override
	public int size() {
		return queue.size();
	}

	
	
}

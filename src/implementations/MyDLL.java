package implementations;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import utilities.ListADT;
import utilities.Iterator;

/**
 * Repeat steps 3–4 for the utility class MyDLL.java using a linked list as the underlying data
 * structure to store each element in a node, based on the same ListADT.java and
 * Iterator.java interfaces provided by your instructor.
 * a. Your implementation should also include a MyDLLNode.java class to store each
 * individual element in the DLL. 
 * @author Mace Howald
 */

public class MyDLL<E> implements ListADT<E> {
	
	private static final long serialVersionUID = 1L;
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;
	
	public MyDLL() {
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		// throw exceptions if element is null or out of bounds
		if (toAdd == null) {
            throw new NullPointerException("Cannot add null element.");
        }
		if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
		
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
		
		if (index == 0) {
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        } else if (index == size) {
            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        } else {
            MyDLLNode<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
        return true;
	}
	
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null) {
            throw new NullPointerException("Cannot add null element.");
        }
        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
	}
	
	
	@Override
	public boolean addAll( ListADT<? extends E> toAdd ) throws NullPointerException {
		if (toAdd == null) {
            throw new NullPointerException("Cannot add null list.");
        }
        Iterator<? extends E> iterator = toAdd.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
        return true;
	}
	
	@Override
	public E get( int index ) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
	}
	
	@Override
	public E remove( int index ) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        MyDLLNode<E> removedNode;

        if (index == 0) {
            removedNode = head;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
        } else if (index == size - 1) {
            removedNode = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            MyDLLNode<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            removedNode = current;
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
        return removedNode.data;
	}
	
	@Override
	public E remove( E toRemove ) throws NullPointerException {
		if (toRemove == null) {
            throw new NullPointerException("Cannot remove null element.");
        }
        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.data.equals(toRemove)) {
                if (current.prev == null) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }
                } else if (current.next == null) {
                    tail = current.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return current.data;
            }
            current = current.next;
        }
        return null;
	}
	
	@Override
	public E set( int index, E toChange ) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange == null) {
            throw new NullPointerException("Cannot set null element.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldData = current.data;
        current.data = toChange;
        return oldData;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public boolean contains( E toFind ) throws NullPointerException {
		// throw exception if null
		if (toFind == null) {
            throw new NullPointerException("Cannot find null element.");
        }
        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.data.equals(toFind)) {
                return true;
            }
            current = current.next;
        }
        return false;
	}
	
	@Override
	public E[] toArray( E[] toHold ) throws NullPointerException {
		if (toHold == null) {
            throw new NullPointerException("Cannot use null array.");
        }
        if (toHold.length < size) {
        	toHold = (E[]) Array.newInstance(toHold.getClass().getComponentType(), size);
        }
        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
            toHold[i++] = current.data;
            current = current.next;
        }
        if (toHold.length > size) {
            toHold[size] = null;
        }
        return toHold;
	}
	
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        return array;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new MyDLLIterator();
	}
	
	private class MyDLLIterator implements Iterator<E> {
        private MyDLLNode<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = current.data;
            current = current.next;
            return data;
        }
    }
}

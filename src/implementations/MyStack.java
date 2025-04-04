package implementations;

import java.util.EmptyStackException;
import java.io.Serializable;
import utilities.StackADT;
import utilities.Iterator;
import utilities.ListADT;

public class MyStack<E> implements StackADT<E>, Serializable {
	private static final long serialVersionUID = 1L;

	private MyArrayList<E> list;
	public MyStack() {
		list = new MyArrayList<>();
	}

	@Override
	public void push(E toAdd) throws NullPointerException {
		if (toAdd == null)
			throw new NullPointerException("Cannot push null onto the stack.");
		list.add(toAdd); // push to top
	}

	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return list.remove(list.size() - 1); // remove from top
	}

	@Override
	public E peek() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return list.get(list.size() - 1); // return top element
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return list.toArray(holder);
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		return list.contains(toFind);
	}

	@Override
	public int search(E toFind) {
		if (toFind == null)
			throw new NullPointerException("Search element cannot be null.");

		int positionFromTop = 1;
		for (int i = list.size() - 1; i >= 0; i--, positionFromTop++) {
			if (toFind.equals(list.get(i)))
				return positionFromTop;
		}
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public boolean equals(StackADT<E> that) {
		if (that == null)
			throw new NullPointerException("Compared stack is null.");

		if (this.size() != that.size())
			return false;

		Iterator<E> thisIter = this.iterator();
		Iterator<E> thatIter = that.iterator();

		while (thisIter.hasNext() && thatIter.hasNext()) {
			E a = thisIter.next();
			E b = thatIter.next();
			if (!a.equals(b))
				return false;
		}
		return true;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean stackOverflow() {
		return false;
	}
}

package implementations;

import java.util.EmptyStackException;
import java.io.Serializable;
import utilities.StackADT;
import utilities.Iterator;

/**
 * An implementation of stack using MyArrayList.
 *
 * @param <E> the type of elements in this stack
 */
public class MyStack<E> implements StackADT<E>, Serializable
{
	private static final long serialVersionUID = 1L;

	private MyArrayList<E> list; // list to store stack elements

	public MyStack()
	{
		list = new MyArrayList<>();
	}

	/**
	 * Adds an element to the top of the stack.
	 */
	@Override
	public void push(E toAdd) throws NullPointerException
	{
		if (toAdd == null)
			throw new NullPointerException("Cannot push null onto the stack.");
		list.add(toAdd); // push to top
	}

	@Override
	public E pop() throws EmptyStackException
	{
		if (isEmpty())
			throw new EmptyStackException();
		return list.remove(list.size() - 1); // remove from top
	}

	@Override
	public E peek() throws EmptyStackException
	{
		if (isEmpty())
			throw new EmptyStackException();
		return list.get(list.size() - 1); // return top element
	}

	@Override
	public void clear()
	{
		list.clear();
	}

	@Override
	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	@Override
	public Object[] toArray()
	{
		Object[] baseArray = list.toArray();
		Object[] reversed = new Object[baseArray.length];

		for (int i = 0; i < baseArray.length; i++)
		{
			reversed[i] = baseArray[baseArray.length - 1 - i];
		}
		return reversed;
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException
	{
		if (holder == null)
			throw new NullPointerException("Holder array cannot be null.");

		int size = list.size();
		if (holder.length < size)
		{
			holder = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), size);
		}

		for (int i = 0; i < size; i++)
		{
			holder[i] = list.get(size - 1 - i); // reverse order
		}

		// Set trailing nulls if holder is larger
		for (int i = size; i < holder.length; i++)
		{
			holder[i] = null;
		}

		return holder;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		return list.contains(toFind); // checks if the stack contains a given element
	}

	@Override
	public int search(E toFind)
	{
		if (toFind == null)
			throw new NullPointerException("Search element cannot be null.");

		int positionFromTop = 1;
		for (int i = list.size() - 1; i >= 0; i--, positionFromTop++)
		{
			if (toFind.equals(list.get(i)))
				return positionFromTop;
		}
		return -1;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new Iterator<E>()
		{
			private int currentIndex = list.size() - 1;

			@Override
			public boolean hasNext()
			{
				return currentIndex >= 0;
			}

			@Override
			public E next()
			{
				if (!hasNext())
				{
					throw new java.util.NoSuchElementException("No more elements in the stack.");
				}
				return list.get(currentIndex--);
			}
		};
	}

	@Override
	public boolean equals(StackADT<E> that)
	{
		if (that == null)
			throw new NullPointerException("Compared stack is null.");

		if (this.size() != that.size()) // checks if this stack is equal to another
			return false;

		Iterator<E> thisIter = this.iterator();
		Iterator<E> thatIter = that.iterator();

		while (thisIter.hasNext() && thatIter.hasNext())
		{
			E a = thisIter.next();
			E b = thatIter.next();
			if (!a.equals(b))
				return false;
		}
		return true;
	}

	@Override
	public int size()
	{
		return list.size();
	}

	@Override
	public boolean stackOverflow()
	{
		return false;
	}
}

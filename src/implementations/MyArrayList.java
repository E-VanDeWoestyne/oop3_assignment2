package implementations;

import java.util.NoSuchElementException;
import utilities.ListADT;
import utilities.Iterator;

/**
 * An implementation of the ListADT interface using an array as the underlying
 * data structure.
 *
 * @author Ethan Van De Woestyne
 */
public class MyArrayList<E> implements ListADT<E>
{

	private static final int DEFAULT_CAPACITY = 10;
	private E[] elements;
	private int size;

	/**
	 * Constructs an empty list with an initial capacity of 10.
	 */
	public MyArrayList()
	{
		elements = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	/**
	 * Increases the capacity of this ArrayList instance, if necessary, to ensure
	 * that it can hold at least the number of elements specified.
	 *
	 * @param minCapacity the desired minimum capacity
	 */
	private void ensureCapacity(int minCapacity)
	{
		if (minCapacity > elements.length)
		{
			int newCapacity = Math.max(elements.length * 2, minCapacity);
			E[] newElements = (E[]) new Object[newCapacity];
			for (int i = 0; i < size; i++)
			{
				newElements[i] = elements[i];
			}
			elements = newElements;
		}
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < size; i++)
		{
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("Cannot add null element");
		}
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}

		ensureCapacity(size + 1);

		for (int i = size; i > index; i--)
		{
			elements[i] = elements[i - 1];
		}

		elements[index] = toAdd;
		size++;
		return true;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("Cannot add null element");
		}

		ensureCapacity(size + 1);
		elements[size++] = toAdd;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("Cannot add null collection");
		}

		if (toAdd.isEmpty())
		{
			return false;
		}

		Iterator<? extends E> iterator = toAdd.iterator();
		while (iterator.hasNext())
		{
			add(iterator.next());
		}

		return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}

		return elements[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}

		E removedElement = elements[index];

		for (int i = index; i < size - 1; i++)
		{
			elements[i] = elements[i + 1];
		}

		elements[--size] = null;

		return removedElement;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException
	{
		if (toRemove == null)
		{
			throw new NullPointerException("Cannot remove null element");
		}

		for (int i = 0; i < size; i++)
		{
			if (toRemove.equals(elements[i]))
			{
				return remove(i);
			}
		}

		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toChange == null)
		{
			throw new NullPointerException("Cannot set null element");
		}
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}

		E oldElement = elements[index];
		elements[index] = toChange;
		return oldElement;
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		if (toFind == null)
		{
			throw new NullPointerException("Cannot search for null element");
		}

		for (int i = 0; i < size; i++)
		{
			if (toFind.equals(elements[i]))
			{
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		if (toHold == null)
		{
			throw new NullPointerException("Array cannot be null");
		}

		if (toHold.length < size)
		{
			toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
		}

		System.arraycopy(elements, 0, toHold, 0, size);

		if (toHold.length > size)
		{
			toHold[size] = null;
		}

		return toHold;
	}

	@Override
	public Object[] toArray()
	{
		Object[] result = new Object[size];
		System.arraycopy(elements, 0, result, 0, size);
		return result;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new MyArrayListIterator();
	}

	/**
	 * Private iterator implementation for MyArrayList.
	 */
	private class MyArrayListIterator implements Iterator<E>
	{
		private int currentIndex = 0;

		@Override
		public boolean hasNext()
		{
			return currentIndex < size;
		}

		@Override
		public E next() throws NoSuchElementException
		{
			if (!hasNext())
			{
				throw new NoSuchElementException("No more elements in the list");
			}

			return elements[currentIndex++];
		}
	}
}
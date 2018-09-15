import java.lang.reflect.Array;

public class ArrayList<T> implements List<T> 
{
	private static class ArrayPosition<T> implements Position<T> 
	{
		//code given by lecturer to implement methods

		T element;
		int index;

		public ArrayPosition(T element, int index) 
		{
			this.element = element;
			this.index = index;
		}

		@Override
		public T element() 
		{
			return element;
		}

		//toString method to display output as {element, index} 
		public String toString() 
		{
			return "{" + element + ", " + index + "}";
		}
	}

	private ArrayPosition<T>[] array;
	private int size;

	public ArrayList() 
	{
		this(4);
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) 
	{
		// NOTE: Here, we are forced to use the Array.newInstance(...) method instead of
		// the traditional new Object[...] syntax because Java cannot handle creating an
		// array where the class uses generics (i.e. ArrayPosition<T>)
		array = (ArrayPosition<T>[]) Array.newInstance(ArrayPosition.class, capacity);
		size = 0;
	}

	@Override
	//size of list
	public int size() 
	{
		return size;
	}

	@Override
	//check if list is empty
	public boolean isEmpty() 
	{
		return size == 0;
	}

	//code given by lecturer
	@SuppressWarnings("unchecked")
	private ArrayPosition<T> insertAtRank(int rank, T element) 
	{
		if (rank < 0 || rank > size) throw new ListBoundsException("List is empty");
		if (size == array.length) 
		{
			ArrayPosition<T>[] newArray = (ArrayPosition<T>[]) Array.newInstance(ArrayPosition.class, array.length*2);
			for(int i=0; i<size;i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}

		// Modified Shift Operation...
		for (int i=size;i>rank;i--) 
		{
			array[i] = array[i-1];

			// NOTE: Extra line is added below to update index of positions while
			// shifting.
			array[i].index = i;
		}
		array[rank] = new ArrayPosition<T>(element, rank);
		size++;
		return array[rank];
	}

	@Override
	//returns the first element of the list
	public Position<T> first() 
	{
		if(isEmpty())throw new ListBoundsException();
		{
			return array[0];
		}
	}

	@Override
	//returns the last element of the list
	public Position<T> last() 
	{
		if(isEmpty())throw new ListBoundsException();
		{
			return array[size-1];
		}
	}

	@Override
	//returns the precious element in the list
	public Position<T> prev(Position<T> p) 
	{
		for(int i=0; i<size; i++)
		{
			if(array[i] == p)
			{
				//error if it's the first element- previous will be null
				if(i == 0)
					throw new ListBoundsException();
				else
				{
					return array[i-1];
				}
			}
		}
		return null;
	}

	@Override
	//returns the next element
	public Position<T> next(Position<T> n) 
	{
		for(int i=0; i<size; i++)
		{
			if(array[i] == n)
			{
				return array[i+1];
			}
		}
		return null;
	}

	@Override
	//insert element at the beginning of list
	public Position<T> insertFirst(T e) 
	{
		return insertAtRank(0, e);
	}

	@Override
	//insert element at end of list
	public Position<T> insertLast(T e) 
	{
		return insertAtRank(size, e);
	}

	@Override
	//insert element e before element at position p
	public Position<T> insertBefore(Position<T> p, T e)
	{
		//as long as list is not empty
		if(!isEmpty())
		{
			for(int i=0; i<size; i++)
			{
				//find position
				if(array[i] == p)
				{
					return insertAtRank(i, e);
				}
			}
		}
		return null;
	}

	@Override
	//insert element e after element at position p
	public Position<T> insertAfter(Position<T> p, T e) 
	{
		for (int i=0; i<size; i++)
		{
			//find position p
			if(array[i] == p)
			{
				//insert after current position
				return insertAtRank(i+1, e);
			}
		}
		return null;
	}

	@Override
	//replace element at position p with e
	public T replace(Position<T> p, T e) 
	{
		if(!isEmpty())
		{
			for(int i=0; i<size; i++)
			{
				if (array[i] == p)
				{
					return array[i].element = e;
				}
			}
		}
		return null;
	}

	@Override
	//removes an element at position p
	public T remove(Position<T> p) 
	{
		ArrayPosition<T> removed = null;
		int tmp = 0;
		for (int i = 0; i < size; i++) 
		{
			if (array[i] == p) 
			{ 
				//find position of element to remove
				tmp = i;
				removed = array[i];
				//decrease size
				size--;
			}
		}

		for (int i=tmp; i<size; i++) 
		{
			//move index on
			array[i] = array[i + 1];
		}
		return (T) removed;

	}

	//toString method to display output as size : space delimited list of items in the queue
	public String toString()
	{
		String string = size + " : ";
		for(int i=0; i<size; i++)
		{
			string += array[i].element + " ";
		}
		return string;
	}

}

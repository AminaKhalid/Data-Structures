package dsaii.list;

import java.lang.reflect.Array;
import java.util.Iterator;

import dsaii.common.Position;

public class ArrayList<T> implements List<T> {
	private static class ArrayPosition<T> implements Position<T> {
		T element;
		int index;
		
		public ArrayPosition(T element, int index) {
			this.element = element;
			this.index = index;
		}

		@Override
		public T element() {
			return element;
		}
		
		public String toString() {
			return "{" + element + "," + index + "}";
		}
	}
	
	private ArrayPosition<T>[] array;
	private int size;
	
	public ArrayList() {
		this(4);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		// NOTE: Here, we are forced to use the Array.newInstance(...) method instead of
		// the traditional new Object[...] syntax because Java cannot handle creating an
		// array where the class uses generics (i.e. ArrayPosition<T>)
		array = (ArrayPosition<T>[]) Array.newInstance(ArrayPosition.class, capacity);
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * This method implements a modified insertAtRank(...) operation for use in the
	 * position based insertion operations 
	 * 
	 * @param rank
	 * @param element
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ArrayPosition<T> insertAtRank(int rank, T element) {
		if (rank < 0 || rank > size) throw new ListBoundsException("List is empty");
		if (size == array.length) {
			ArrayPosition<T>[] newArray = (ArrayPosition<T>[]) Array.newInstance(ArrayPosition.class, array.length*2);
			for(int i=0; i<size;i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
		
		// Modified Shift Operation...
		for (int i=size;i>rank;i--) {
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
	public Position<T> first() {
		return array[0];
	}

	@Override
	public Position<T> last() {
		return array[size-1];
	}

	@Override
	public Position<T> prev(Position<T> p) {
		ArrayPosition<T> pos = (ArrayPosition<T>) p;
		if (pos.index == 0) throw new ListBoundsException("No previous node");
		return array[pos.index-1];
	}

	@Override
	public Position<T> next(Position<T> p) {
		ArrayPosition<T> pos = (ArrayPosition<T>) p;
		if (pos.index == (size()-1)) throw new ListBoundsException("No next node");
		return array[pos.index+1];
	}

	@Override
	public Position<T> insertFirst(T e) {
		return insertAtRank(0, e);
	}

	@Override
	public Position<T> insertLast(T e) {
		return insertAtRank(size, e);
	}

	@Override
	public Position<T> insertBefore(Position<T> p, T e) {
		return insertAtRank(((ArrayPosition<T>) p).index, e);
	}

	@Override
	public Position<T> insertAfter(Position<T> p, T e) {
		return insertAtRank(((ArrayPosition<T>) p).index+1, e);
	}

	@Override
	public T replace(Position<T> p, T e) {
		ArrayPosition<T> pos = (ArrayPosition<T>) p;
		T temp = pos.element;
		pos.element = e;
		return temp;
	}

	@Override
	public T remove(Position<T> p) {
		ArrayPosition<T> pos = (ArrayPosition<T>) p;
		if (pos.index < 0 || pos.index > size-1) {
			throw new ListBoundsException("Invalid position");
		}
		
		for (int i=pos.index;i<size-1;i++) {
			array[i] = array[i+1];
			array[i].index = i;
		}
		
		size--;
		return pos.element;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i=0;i<size;i++) {
			buf.append(array[i].element).append(" ");
		}
		return buf.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int index = 0;
			
			@Override
			public boolean hasNext() {
				return index < size();
			}

			@Override
			public T next() {
				return array[index++].element;
			}
		};
	}
}

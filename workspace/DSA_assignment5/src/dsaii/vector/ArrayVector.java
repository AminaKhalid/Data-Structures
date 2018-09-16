package dsaii.vector;

import java.util.Iterator;

public class ArrayVector<T> implements Vector<T>,Iterable<T> {
	private T[] array;
	private int size;

	public ArrayVector() {
		this(1);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayVector(int capacity) {
		array = (T[]) new Object[capacity];
		size = 0;
	}
	
	@Override
	public T elemAtRank(int rank) {
		if (rank < 0 || rank > size-1) {
			throw new RankOutOfBoundsException();
		}
		return array[rank];
	}

	@Override
	public T replaceAtRank(int rank, T element) {
		if (rank < 0 || rank > size-1) {
			throw new RankOutOfBoundsException();
		}
		T temp = array[rank];
		array[rank] = element;
		return temp;
	}

	@Override
	public void insertAtRank(int rank, T element) {
		if (rank < 0 || rank > size) {
			throw new RankOutOfBoundsException();
		}
		
		if (size == array.length) {
//			System.out.println("Array Full ("+size+") ! - extend and copy...");
			T[] newArray = (T[]) new Object[array.length*2];
			for(int i=0; i<size;i++) {
//				System.out.println("copying index " + i +": " +array[i] );
				newArray[i] = array[i];
			}
//			System.out.println("Done: " + array);
			array = newArray;
		}
		for (int i=size;i>rank;i--) {
			array[i] = array[i-1];
		}
		array[rank] = element;
		size++;
	}

	@Override
	public T removeAtRank(int rank) {
		if (rank < 0 || rank > size-1) {
			throw new RankOutOfBoundsException();
		}
		T temp = array[rank];
		
		for (int i=rank;i<size-1;i++) {
			array[i] = array[i+1];
		}
		
		size--;
		return temp;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		for (int i=0;i<size;i++) {
			buf.append(array[i]).append(" ");	
		}
		return buf.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public T next() {
				return array[index++];
			}
		};
	}
}

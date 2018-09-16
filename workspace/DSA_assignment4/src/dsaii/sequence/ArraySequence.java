package dsaii.sequence;

import java.lang.reflect.Array;
import java.util.Iterator;

import dsaii.common.Position;
import dsaii.tree.InvalidPositionException;
import dsaii.vector.RankOutOfBoundsException;

public class ArraySequence<T> implements Sequence<T> {
	private static class ArrayPosition<T> implements Position<T> {
		int index;
		T element;
		
		public ArrayPosition(T element, int index) {
			this.element = element;
			this.index = index;
		}
		
		public T element() {
			return element;
		}
	}
	
	ArrayPosition<T>[] array;
	int size;
	
	public ArraySequence() {
		this(50);
	}
	
	@SuppressWarnings("unchecked")
	public ArraySequence(int capacity) {
		array = (ArrayPosition<T>[]) Array.newInstance(ArrayPosition.class, capacity);
		size = 0;
	}
	
	@Override
	public T elemAtRank(int rank) {
		if (rank < 0 || rank > size-1) {
			throw new RankOutOfBoundsException();
		}
		return array[rank].element;
	}

	@Override
	public T replaceAtRank(int rank, T element) {
		if (rank < 0 || rank > size-1) {
			throw new RankOutOfBoundsException();
		}
		T temp = array[rank].element;
		array[rank].element = element;
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insertAtRank(int rank, T element) {
		if (rank < 0 || rank > size) {
			throw new RankOutOfBoundsException();
		}
		
		// Extend the Array
		if (size == array.length) {
			ArrayPosition<T>[] newArray = (ArrayPosition<T>[]) Array.newInstance(ArrayPosition.class, array.length*2);
			for(int i=0; i<size;i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
		
		for (int i=size;i>rank;i--) {
			array[i] = array[i-1];
			array[i].index = i;
		}
		
		array[rank] = new ArrayPosition<T>(element,rank);
		size++;
	}

	@Override
	public T removeAtRank(int rank) {
		if (rank < 0 || rank > size-1) {
			throw new RankOutOfBoundsException();
		}
		
		T temp = array[rank].element;
		
		for (int i=rank;i<size-1;i++) {
			array[i] = array[i+1];
			array[i].index = i;
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
			buf.append(array[i].element).append(" ");	
		}
		return buf.toString();
	}

	@Override
	public Position<T> first() {
		if (size == 0) throw new InvalidPositionException("Sequence is empty");
		return array[0];
	}

	@Override
	public Position<T> last() {
		if (size == 0) throw new InvalidPositionException("Sequence is empty");
		return array[size-1];
	}

	@Override
	public Position<T> prev(Position<T> p) {
		ArrayPosition<T> pos = (ArrayPosition<T>) p;
		if (pos.index == 0) throw new InvalidPositionException("At start of Sequence");
		return array[pos.index-1];
	}

	@Override
	public Position<T> next(Position<T> p) {
		ArrayPosition<T> pos = (ArrayPosition<T>) p;
		if (pos.index == size-1) throw new InvalidPositionException("At end of Sequence");
		return array[pos.index+1];
	}

	@Override
	public Position<T> insertFirst(T e) {
		insertAtRank(0, e);
		return atRank(0);
	}

	@Override
	public Position<T> insertLast(T e) {
		insertAtRank(size, e);
		return atRank(size);
	}

	@Override
	public Position<T> insertBefore(Position<T> p, T e) {
		int rank = rankOf(p);
		insertAtRank(rank, e);
		return atRank(rank);
	}

	@Override
	public Position<T> insertAfter(Position<T> p, T e) {
		int rank = rankOf(p)+1;
		insertAtRank(rank, e);
		return atRank(rank);
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
		return removeAtRank(rankOf(p));
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
				return array[index++].element;
			}
		};
	}

	@Override
	public Position<T> atRank(int rank) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		
		return array[rank];
	}

	@Override
	public int rankOf(Position<T> p) {
		return ((ArrayPosition<T>) p).index;
	}
}

package dsaii.tree;

import java.lang.reflect.Array;

import dsaii.common.Position;

public class AbstractArrayBasedBinaryTree<T> extends AbstractBinaryTree<T> {
	protected static class ArrayPosition<T> implements Position<T> {
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
	
	protected ArrayPosition<T>[] array;
	protected int size;
	protected int height;
	
	// Used to create
	private int pow(int a, int b) {
		return (b==0) ? 1:(a * pow(a, b-1));
	}
	
	public AbstractArrayBasedBinaryTree() {
		this(5);
	}
	
	@SuppressWarnings("unchecked")
	protected void expandTree() {
		if (height == 31) throw new RuntimeException("Tree cannot be expanded");
		
		ArrayPosition<T>[] array2 = (ArrayPosition<T>[]) Array.newInstance(ArrayPosition.class, pow(2, height+1));
		for (int i=0;i<array.length;i++) {
			array2[i] = array[i];
		}
		array = array2;
		height++;
	}
	
	@SuppressWarnings("unchecked")
	public AbstractArrayBasedBinaryTree(int height) {
		array = (ArrayPosition<T>[]) Array.newInstance(ArrayPosition.class, pow(2, height));
		this.height = height;
		size = 0;
	}
	
	@Override
	public Position<T> root() {
		if (isEmpty()) throw new InvalidPositionException("No root exists");
		return array[0];
	}

	@Override
	public Position<T> parent(Position<T> p) {
		ArrayPosition<T> pos = (ArrayPosition<T>) p;
		if (pos.index == 0) throw new InvalidPositionException("No parent exists");
		
		return array[(pos.index-1)/2];
	}

	@Override
	public boolean isInternal(Position<T> p) {
		return hasLeft(p) || hasRight(p);
	}

	@Override
	public boolean isExternal(Position<T> p) {
		return !isInternal(p);
	}

	@Override
	public boolean isRoot(Position<T> p) {
		return ((ArrayPosition<T>) p).index == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public T replace(Position<T> p, T element) {
		T temp = p.element();
		((ArrayPosition<T>) p).element = element;
		return temp;
	}

	@Override
	public Position<T> left(Position<T> p) {
		int left = 2*((ArrayPosition<T>) p).index+1;
		
		if ((left > size) || (array[left] == null)) throw new InvalidPositionException("No left child exists");
		return array[left];
	}

	@Override
	public Position<T> right(Position<T> p) {
		int right = 2*((ArrayPosition<T>) p).index+2;
		
		if ((right > size) || (array[right] == null)) throw new InvalidPositionException("No right child exists");
		return array[right];
	}

	@Override
	public boolean hasLeft(Position<T> p) {
		int left = 2*((ArrayPosition<T>) p).index+1;
		return (left <= size) && (array[left] != null);
	}

	@Override
	public boolean hasRight(Position<T> p) {
		int right = 2*((ArrayPosition<T>) p).index+2;
		return (right <= size) && (array[right] != null);
	}
}

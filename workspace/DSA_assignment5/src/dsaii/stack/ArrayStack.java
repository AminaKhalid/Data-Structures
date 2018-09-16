package dsaii.stack;

public class ArrayStack<T> implements Stack<T> {
	private T[] array;
	private int top;
	
	public ArrayStack() {
		this(10);
	}

	public ArrayStack(int capacity) {
		array = (T[]) new Object[capacity];
		top = 0;
	}
	
	public int size() {
		return top;
	}
	
	public boolean isEmpty() {
		return top == 0;
	}
	
	public void push(T object) {
		if (top == array.length) throw new StackFullException();
		array[top++] = object;
	}

	public T pop() {
		if (top == 0) throw new StackEmptyException();
		T temp = array[--top];
		array[top] = null;
		return temp;
	}
	
	public T top() {
		if (top == 0) throw new StackEmptyException();
		return array[top-1];
	}
}

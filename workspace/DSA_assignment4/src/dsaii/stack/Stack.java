package dsaii.stack;

public interface Stack<T> {
	public int size();
	public boolean isEmpty();
	public void push(T object);
	public T pop();
	public T top();
}

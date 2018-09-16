package dsaii.queue;

public interface Queue<T> {
	public void enqueue(T object);
	public T dequeue();
	public T front();
	
	public int size();
	public boolean isEmpty();
}

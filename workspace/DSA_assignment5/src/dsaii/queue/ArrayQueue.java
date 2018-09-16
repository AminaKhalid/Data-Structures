package dsaii.queue;

public class ArrayQueue<T> implements Queue<T> {
	private T[] array;
	private int rear;
	private int front;
	
	public ArrayQueue() {
		this(5);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
		array = (T[]) new Object[capacity];
		rear = 0;
		front = 0;
	}
	
	@Override
	public void enqueue(T object) {
		// NOTE: This was not in the notes, but you need to check if the queue
		// is full. This happens when the array has only one free cell left in
		// it (i.e. N = size+1) because, if the array is used in its entirety,
		// then front = rear, which is the same as when the array is empty (so
		// the algorithm will get confused).
		if (array.length == size()+1) throw new QueueFullException();
		array[rear] = object;
		rear = (rear + 1) % array.length;
	}

	@Override
	public T dequeue() {
		// NOTE: Need to check if the queue is empty, and if so, throw an exception 
		if (front==rear) throw new QueueEmptyException();
		T temp = array[front];
		array[front] = null;
		front = (front + 1) % array.length;
		return temp;
	}

	@Override
	public T front() {
		// NOTE: Need to check if the queue is empty, and if so, throw an exception 
		if (front==rear) throw new QueueEmptyException();
		
		return array[front];
	}

	@Override
	public int size() {
		return (array.length+rear-front)%array.length;
	}

	@Override
	public boolean isEmpty() {
		return rear == front;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(size()).append(" / ").append(array.length).append(" :");
		// The code below prints out the array in its current form (so you
		// can see which cells are empty). this is not what was asked for
		// in the practical, but is worth using for debugging purposes.
//		for (int i=0;i<array.length;i++) {
//			buf.append(" ").append(array[i]);
//		}
		
		// The code below prints out the queue in its "natural" order. Notice
		// that I have had to use (N+rear-i)%N to stop the dividend becoming
		// negative (java evalutes -1%N to -1 which is not a valid index into
		// the array - we actually want N-1)
		for (int i=0; i < size(); i++) {
			buf.append(" ").append(array[(front+i)%array.length]);
		}
		
		return buf.toString();
	}
}

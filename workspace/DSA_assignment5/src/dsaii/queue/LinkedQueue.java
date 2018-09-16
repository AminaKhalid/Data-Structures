package dsaii.queue;

public class LinkedQueue<T> implements Queue<T> {
	private class Node {
		T element;
		Node next;
		
		public Node(T element, Node next) {
			this.element = element;
			this.next = next;
		}
	}
	
	private Node front,rear;
	private int size;
	
	@Override
	public void enqueue(T object) {
		Node node = new Node(object, null);
		if (rear == null) {
			front = node;
		} else {
			rear.next = node;
		}
		rear = node;
		size++;
	}

	@Override
	public T dequeue() {
		if (front == null) throw new QueueEmptyException();
		T temp = front.element;
		front = front.next;
		if (front == null) rear = null;
		return temp;
	}

	@Override
	public T front() {
		if (front == null) throw new QueueEmptyException();
		return front.element;
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
		buf.append(size()).append(" :");
		Node cur = front;
		while (cur != null) {
			buf.append(" ").append(cur.element);
			cur = cur.next;
		}
		return buf.toString();
	}
}

package dsaii.queue;

public class Test {
	public static void main(String[] args) {
		Queue<String> queue = new ArrayQueue<String>();
		System.out.println(queue);
		queue.enqueue("H");
		System.out.println(queue);
		queue.enqueue("A");
		System.out.println(queue);
		queue.enqueue("A");
		System.out.println(queue);
		queue.dequeue();
		System.out.println(queue);
		queue.enqueue("P");
		System.out.println(queue);
		queue.enqueue("P");
		System.out.println(queue);
		queue.dequeue();
		System.out.println(queue);
	}
}

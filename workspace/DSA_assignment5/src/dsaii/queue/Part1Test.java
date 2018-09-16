package dsaii.queue;

public class Part1Test {
	public static void main(String[] args) {
		Queue<String> queue = new ArrayQueue<String>(10);
		System.out.println(queue);
		queue.enqueue("R");
		System.out.println(queue);
		queue.enqueue("E");
		System.out.println(queue);
		queue.dequeue();
		System.out.println(queue);
		queue.enqueue("M");
		System.out.println(queue);
		queue.enqueue("H");
		System.out.println(queue);
		queue.enqueue("E");
		System.out.println(queue);
		queue.dequeue();
		System.out.println(queue);
		queue.enqueue("L");
		System.out.println(queue);
		queue.enqueue("L");
		System.out.println(queue);
		queue.dequeue();
		System.out.println(queue);
		queue.enqueue("O");
		System.out.println(queue);
	}
}

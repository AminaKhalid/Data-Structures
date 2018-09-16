package dsaii.tree;

public class TestHeap {

	public static void main(String[] args)
	{
		Heap<Integer> heap = new Heap<Integer>();
		
		heap.insert(24);
		heap.insert(12);
		heap.insert(36);
		heap.insert(5);
		heap.insert(7);
		heap.insert(2);
		System.out.println(heap.toString());
		
	}
}

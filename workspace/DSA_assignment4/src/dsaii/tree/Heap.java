package dsaii.tree;

public class Heap<T extends Comparable<T>> extends AbstractArrayBasedBinaryTree<T> {
	public void insert(T element) {
		if (size == array.length) {
			expandTree();

			ArrayPosition<T> position = new ArrayPosition<>(element,size);
			array[size] = position;
			size++;

			//pass to upHeap function
			upHeap(position);
		}

		// TODO: insert & upheap

		array[size] =	new ArrayPosition(element, size);
		size++;

	}

	//upHeap to print in order of min to max
	private void upHeap(ArrayPosition<T> pos)
	{
		if(!isRoot(pos))
		{
			ArrayPosition<T> p = (ArrayPosition<T>)parent(pos);

			//comparing to see if less than 0
			while(pos.element().compareTo(p.element())< 0)
			{
				//swap the indexes
				swap(pos.index, p.index);
			}
		}

		for(int i=1;i< size-1; i++)
		{
			ArrayPosition<T> position = array[i];
			if(position.element.compareTo(parent(position).element())<0)
			{
				ArrayPosition<T> p = (ArrayPosition<T>)
						parent(position);
				swap(position.index, p.index);
			}
		}
	}

	//return min
	public T min() {
		return root().element();
	}

	public T remove() 
	{
		// TODO: copy & downheap
		//switch root with last node
		if(isEmpty()) {
			System.out.println("Empty Heap");
		}
		else if(!(isInternal(root()))){
			//if the root is the only node, remove it
			array[0] = null;
		}
		else {
			
			swap(0,size-1);
			if (hasLeft(root())) {
				ArrayPosition<T> l = (ArrayPosition<T>) left(root());
			}

			if (hasRight(root())) {

				ArrayPosition<T> r = (ArrayPosition<T>) right(root());
			}
		}
		size--;

		return null;
	}


	/**
	 * Thus method is useful for the upheap / downheap operations
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
		T temp = array[i].element;
		array[i].element = array[j].element;
		array[i].index = i;
		array[j].element = temp;
		array[j].index = j;
	}

	private static void test(Heap <Integer> heap)
	{
		//to test the first 6 insert operations
		System.out.println("\nFIRST SIX OPERATIONS\n");
		heap.insert(24);
		System.out.println(heap);
		heap.insert(12);
		System.out.println(heap);
		heap.insert(36);
		System.out.println(heap);
		heap.insert(5);
		System.out.println(heap);
		heap.insert(7);
		System.out.println(heap);
		heap.insert(2);
		System.out.println(heap);

	}

	private static void testAll(Heap <Integer> heap)
	{
		//to test the rest of the operations
		System.out.println("\nALL OPERATIONS\n");
		heap.insert(24);
		heap.insert(12);
		heap.insert(36);
		heap.insert(5);
		heap.insert(7);
		heap.insert(2);
		heap.remove();
		heap.insert(76);
		heap.remove();
		heap.insert(18);
		heap.insert(24);
		heap.insert(11);
		heap.remove();

		System.out.println(heap);
	}

	//main method
	public static void main(String[] argc) 
	{
		Heap<Integer> heap = new Heap<>();
		test(heap);
		testAll(heap);

	}
}

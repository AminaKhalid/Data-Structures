package dsaii.tree;

public class Heap<T extends Comparable<T>> extends AbstractArrayBasedBinaryTree<T> {
	public void insert(T element) {
		if (size == array.length) {
			expandTree();
		}
		
		array[size]=new ArrayPosition<T>(element, size++);
		upheap(size-1);
	}
	
	public T min() {
		return root().element();
	}
	
	public T remove() {
		T temp = array[0].element;
		
		// Copy the last value over the root
		array[0].element = array[size-1].element;
		
		// Dereference the array position...
		array[size-1].element = null;
		array[size-1].index = -1;
		array[size-1] = null;
		size--;
		
		if (size > 0) downheap(0);
		
		return temp;
		
	}
	
	private void upheap(int i) {
		if (i == 0 || array[i].element.compareTo(array[(i-1)/2].element) >= 0) return;
		swap(i, (i-1)/2);
		upheap((i-1)/2);
	}
	
	private void swap(int i, int j) {
		T temp = array[i].element;
		array[i].element = array[j].element;
		array[j].element = temp;
	}

	private void downheap(int i) {
		if (hasLeft(array[i]) && hasRight(array[i])) {
			if (array[2*i+1].element.compareTo(array[2*i+2].element) < 0) {
				swap(i, 2*i+1);
				downheap(2*i+1);
			} else {
				swap(i, 2*i+2);
				downheap(2*i+2);
			}
		} else if (hasLeft(array[i]) && array[i].element.compareTo(array[2*i+1].element) > 0) {
			swap(i, 2*i+1);
			downheap(2*i+1);
		} else if (hasRight(array[i]) && array[i].element.compareTo(array[2*i+2].element) > 0) {
			swap(i, 2*i+2);
			downheap(2*i+2);
		}
	}
}

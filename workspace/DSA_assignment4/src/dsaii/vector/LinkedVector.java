package dsaii.vector;

public class LinkedVector<T> implements Vector<T> {
	/**
	 * Private static nested class that implements the Node
	 * used in the singly linked list implementation. Remember
	 * static nested classes should be preferred to inner classes if
	 * the nested class does not require access to the fields/methods
	 * of the enclosing classes instances.
	 * 
	 * @author rcollier
	 *
	 * @param <T>
	 */
	private static class Node<T> {
		T element;
		Node<T> next;
		
		Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
	}
	
	/**
	 * The reference to the first node in the vector
	 */
	private Node<T> first;
	
	/**
	 * The number of elements stored in the vector
	 */
	private int size;
	
	/**
	 * Default constructor - not really needed because the fields
	 * are automatically assigned these values when an instance is
	 * created.
	 */
	public LinkedVector() {
		this.first = null;
		this.size = 0;
	}
	
	/**
	 * This is the workhorse method. It will be used by all the other
	 * methods to return the Node associated with a given rank.
	 * 
	 * @param rank
	 * @return
	 */
	private Node<T> findNode(int rank) {
		int r = 0;
		Node<T> cur = first;
		while (r < rank) {
			cur = cur.next;
			r++;
		}
		return cur;
	}
	
	/**
	 * Return the element at the given rank
	 */
	@Override
	public T elemAtRank(int rank) {
		if (rank < 0 || rank >= size) {
			throw new RankOutOfBoundsException();
		}
		return findNode(rank).element;
	}

	/**
	 * Update the element at the given rank to a new value
	 * and return the old value.
	 */
	@Override
	public T replaceAtRank(int rank, T element) {
		if (rank < 0 || rank >= size) {
			throw new RankOutOfBoundsException();
		}
		
		Node<T> node = findNode(rank);
		T temp = node.element;
		node.element = element;
		return temp;
	}

	/**
	 * Insert the element at the given rank.
	 */
	@Override
	public void insertAtRank(int rank, T element) {
		if (rank < 0 || rank > size) {
			throw new RankOutOfBoundsException();
		}
		
		// Two cases to deal with - we are inserting at
		// rank 0 or not at rank 0.
		if (rank > 0) {
			// Okay, so we need to get the node at rank-1
			// because this is the node that we will modify
			// to insert the new element into the list.
			Node<T> node = findNode(rank-1);
			
			// The specific update is to create a new node
			// that links to the node at rank "rank" and then
			// to update the node at rank "rank-1" to point to
			// the new node.
			node.next = new Node<T>(element, node.next);
		} else {
			// Okay, its rank 0, so create a new node that 
			// links to whatever first links to, and then
			// make first reference this new node.
			first = new Node<T>(element, first);
		}
		size++;
	}

	@Override
	public T removeAtRank(int rank) {
		if (rank < 0 || rank >= size) {
			throw new RankOutOfBoundsException();
		}
		
		T temp = null;
		
		// Two cases to deal with - we are removing at
		// rank 0 or not at rank 0.
		if (rank > 0) {
			// Okay, so not rank 0. This is like insert
			// We start by finding the node that relates to
			// "rank-1". Use it to get a reference to the
			// element stored at rank (node.next.element) and
			// update nodes link to refer to the node at 
			// "rank+1" (node.next.next)
			Node<T> node = findNode(rank-1);
			temp = node.next.element;
			node.next = node.next.next;
		} else {
			// Okay, so rank 0. Get the value stored in the
			// first node and then update first to refer to 
			// whatever is after the first node (could be null
			// if there is only one node).
			temp = first.element;
			first = first.next;
		}
		size--;
		return temp;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Display the contents of the vector by iterating
	 * through the list, printing out the value stored
	 * at each node in turn (use a space delimiter).
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		Node<T> cur = first;
		while (cur != null) {
			buf.append(cur.element).append(" ");
			cur = cur.next;
		}
		return buf.toString();
	}
}

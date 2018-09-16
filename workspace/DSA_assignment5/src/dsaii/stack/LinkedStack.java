package dsaii.stack;
/**
 * Implementation of a link based stack for COMP20040 that uses a static nested
 * class to implement the Node.
 * 
 * @author rcollier
 *
 * @param <T>
 */
public class LinkedStack<T> implements Stack<T> {
	/**
	 * This static nested class implements the concept of a Node in the linked
	 * list implementation. It is preferred to an inner class because it does
	 * not require access to any of the methods of the enclosing class.  Inner
	 * classes maintain unnecessary references to the enclosing object.
	 * 
	 * @author rcollier
	 *
	 * @param <T>
	 */
	private static class Node<T> {
		private T element;
		private Node<T> next;
		
		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
	}
	
	/**
	 * Field to reference the top node in the stack
	 */
	private Node<T> top;
	
	/**
	 * Field to represent the number of elements stored in the stack.
	 */
	private int size;
	
	/**
	 * Constructor that creates an empty stack. Technically this is not
	 * needed because the default value for top is null and the default
	 * value for size is 0.
	 */
	public LinkedStack() {
		top = null;
		size = 0;
	}
	
	/**
	 * Return the number of elements stored in the stack
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Indicates whether or not the stack is empty
	 * 
	 * @return true if the stack is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Inserts an element on top of the stack. The new nodes next field 
	 * references the previous top node, and the size is incremented separately.
	 */
	@Override
	public void push(T object) {
		top = new Node<T>(object, top);
		size++;
	}

	/**
	 * Removes to top element from the stack and returns it.
	 * 
	 * @return an element of type T
	 */
	@Override
	public T pop() {
		if (size == 0) throw new StackEmptyException();

		T temp = top.element;
		top = top.next;
		size--;
		
		return temp;
	}

	/**
	 * Returns the element on top of the stack
	 * 
	 * @return an element of type T
	 */
	@Override
	public T top() {
		if (size == 0) throw new StackEmptyException();
		
		return top.element;
	}

}

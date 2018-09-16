package dsaii.list;

import java.util.Iterator;

import dsaii.common.Position;

public class LinkedList<T> implements List<T> {
	private static class Node<T> implements Position<T> {
		private T element;
		private Node<T> prev;
		private Node<T> next;
		
		@Override
		public T element() {
			return element;
		}

		public Node(T element, Node<T> prev, Node<T> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
		
		public String toString() {
			return element.toString();
		}
	}

	public Node<T> first;
	public Node<T> last;
	public int size;
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<T> first() {
		if (first == null) throw new ListBoundsException("List is empty");
		return first;
	}

	@Override
	public Position<T> last() {
		if (last == null) throw new ListBoundsException("List is empty");
		return last;
	}

	@Override
	public Position<T> prev(Position<T> p) {
		Node<T> node = ((Node<T>) p);
		if (node.prev == null) throw new ListBoundsException("No previous node");
		return node.prev;
	}

	@Override
	public Position<T> next(Position<T> p) {
		Node<T> node = ((Node<T>) p);
		if (node.next == null) throw new ListBoundsException("No next node");
		return node.next;
	}

	@Override
	public Position<T> insertFirst(T e) {
		Node<T> node = new Node<T>(e, null, first);
		
		if (first == null) { 
			last = node;
		} else { 
			first.prev = node;
		}
		
		first = node;
		size++;
		return node;
	}

	@Override
	public Position<T> insertLast(T e) {
		Node<T> node = new Node<T>(e, last, null);
		
		if (first == null) { 
			first = node;
		} else { 
			last.next = node;
		}
		
		last = node;
		size++;
		return node;
	}

	@Override
	public Position<T> insertBefore(Position<T> p, T e) {
		if (p == first) return insertFirst(e);
		
		Node<T> n = (Node<T>) p;
		Node<T> node = new Node<T>(e, n.prev, n);
		n.prev.next = node;
		n.prev = node;
		size++;
		return node;
	}

	@Override
	public Position<T> insertAfter(Position<T> p, T e) {
		if (p == last) return insertLast(e);
		
		Node<T> n = (Node<T>) p;
		Node<T> node = new Node<T>(e, n, n.next);
		n.next.prev = node;
		n.next = node;
		size++;
		return node;
	}

	@Override
	public T replace(Position<T> p, T e) {
		Node<T> node = (Node<T>) p;
		T temp = node.element;
		node.element = e;
		return temp;
	}

	@Override
	public T remove(Position<T> p) {
		Node<T> n = (Node<T>) p;
		
		if (p == last) {
			last = n.prev;
		} else {
			n.next.prev = n.prev;
		}
		
		if (p == first) {
			first = n.next;
		} else {
			n.prev.next = n.next;
		}
		
		n.prev = null;
		n.next = null;
		size--;
		return n.element;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		Node<T> cur = first;
		while (cur != null) {
			buf.append(cur.element()).append(" ");
			cur = cur.next;
		}
		return buf.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
		    private Position<T> current;
		    
			@Override
			public boolean hasNext() {
		        if (isEmpty()) return false;
		        if (current == null) return true;
		        return !current.equals(last);
	        }

			@Override
			public T next() {
		        if (current == null) {
		        	current = first;
	        	} else {
	        		current = LinkedList.this.next(current);
        		}
		        return current.element();
		        }
			
		};
	}
}

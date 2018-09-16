package dsaii.tree;

import java.util.Iterator;

import dsaii.common.Position;
import dsaii.list.LinkedList;
import dsaii.list.List;

public class AbstractLinkBasedBinaryTree<T> implements BinaryTree<T> {
	protected class Node implements Position<T> {
		T element;
		Node parent;
		Node left;
		Node right;
		
		public Node(T element, Node parent) {
			this.element = element;
			this.parent = parent;
		}
		
		@Override
		public T element() {
			return element;
		}
	}
	
	protected Node root;
	protected int size;
	
	public AbstractLinkBasedBinaryTree() {
		root = null;
		size = 0;
	}
	
	@Override
	public Position<T> root() {
		if (root == null) throw new InvalidPositionException("No root exists");
		return root;
	}

	@Override
	public Position<T> parent(Position<T> p) {
		Node node = (Node) p;
		if (node.parent == null) throw new InvalidPositionException("No parent exists");
		return node.parent;
	}

	@Override
	public Iterator<Position<T>> children(Position<T> p) {
		Node node = (Node) p;
		List<Position<T>> list = new LinkedList<Position<T>>();
		if (node.left != null) list.insertLast(node.left);
		if (node.right != null) list.insertLast(node.right);
		return list.iterator();
	}

	@Override
	public boolean isInternal(Position<T> p) {
		Node node = (Node) p;
		return node.left != null || node.right != null;
	}

	@Override
	public boolean isExternal(Position<T> p) {
		Node node = (Node) p;
		return node.left == null && node.right == null;
	}

	@Override
	public boolean isRoot(Position<T> p) {
		return p == root;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<Position<T>> positions() {
		List<Position<T>> list = new LinkedList<Position<T>>();
		visitPositions(root, list);
		return list.iterator();
	}

	private void visitPositions(Node node, List<Position<T>> list) {
		if (node == null) return;
		
		list.insertLast(node);
		visitPositions(node.left, list);
		visitPositions(node.right, list);
	}

	@Override
	public T replace(Position<T> p, T t) {
		Node node = (Node) p;
		T temp = node.element;
		node.element = t;
		return temp;
	}

	@Override
	public Iterator<T> iterator() {
		List<T> list = new LinkedList<T>();
		visitPositions2(root, list);
		return list.iterator();
	}
	
	private void visitPositions2(Node node, List<T> list) {
		if (node == null) return;
		
		list.insertLast(node.element);
		visitPositions2(node.left, list);
		visitPositions2(node.right, list);
	}


	@Override
	public Position<T> left(Position<T> p) {
		Node node = (Node) p;
		if (node.left == null) throw new InvalidPositionException("No left child exists");
		return node.left;
	}

	@Override
	public Position<T> right(Position<T> p) {
		Node node = (Node) p;
		if (node.right == null) throw new InvalidPositionException("No right child exists");
		return node.right;
	}

	@Override
	public boolean hasLeft(Position<T> p) {
		return ((Node)p).left != null;
	}

	@Override
	public boolean hasRight(Position<T> p) {
		return ((Node)p).right != null;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buildString(root, "", buf);
		return buf.toString();
	}
	
	private void buildString(Position<T> p, String offset, StringBuffer buf) {
		if (p == null) return;
		buf.append(offset);
		buf.append(p.element());
		buf.append("\n");
		if (hasLeft(p)) buildString(left(p), offset+"\t", buf);
		if (hasRight(p)) buildString(right(p), offset+"\t", buf);
	}
}

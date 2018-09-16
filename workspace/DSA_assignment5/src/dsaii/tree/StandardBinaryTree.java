package dsaii.tree;

import dsaii.common.Position;

public class StandardBinaryTree<T> extends AbstractLinkBasedBinaryTree<T> {
	public Position<T> addRoot(T element) {
		if (root != null) throw new InvalidPositionException("Root already exists");
		size++;
		return root = new Node(element, null);
	}
	
	public Position<T> insertLeft(Position<T> p, T element) {
		Node node = (Node) p;
		if (node.left != null) throw new InvalidPositionException("Left child already exists");
		size++;
		return node.left = new Node(element, node);
	}

	public Position<T> insertRight(Position<T> p, T element) {
		Node node = (Node) p;
		if (node.right != null) throw new InvalidPositionException("Right child already exists");
		size++;
		return node.right = new Node(element, node);
	}
	
	public T remove(Position<T> p) {
		Node node = (Node) p;
		
		if (node.left == null && node.right == null) {
			if (node == root) {
				root = null;
			} else {
				if (node.parent.left == node) {
					node.parent.left = null;
				} else {
					node.parent.right = null;
				}
				node.parent = null;
			}
		} else if (node.left == null) {
			if (node == root) {
				root = node.right;
				node.right.parent = null;
			} else {
				if (node.parent.left == node) {
					node.parent.left = node.right;
					node.right.parent = node.parent;
				} else {
					node.parent.right = node.right;
					node.right.parent = node.parent;
				}
				node.right = null;
				node.parent = null;
			}
		} else if (node.right == null) {
			if (node == root) {
				root = node.left;
				node.left.parent = null;
			} else {
				if (node.parent.left == node) {
					node.parent.left = node.left;
					node.left.parent = node.parent;
				} else {
					node.parent.right = node.left;
					node.left.parent = node.parent;
				}
				node.left = null;
				node.parent = null;
			}
		} else {
			throw new InvalidPositionException("Node has 2 children");
		}
		
		size--;
		return node.element;
	}
	
	public void attach(Position<T> p, StandardBinaryTree<T> left, StandardBinaryTree<T> right) {
		Node node = (Node) p;
		
		if (node.left != null || node.right != null)
			throw new InvalidPositionException("Node has children");
		
		node.left = left.root;
		left.root.parent = node;
		node.right = right.root;
		right.root.parent = node;
		
		size += left.size + right.size;
	}
}

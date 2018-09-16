package dsaii.tree;

import dsaii.common.Position;

public class ProperBinaryTree<T> extends AbstractLinkBasedBinaryTree<T> {
	public ProperBinaryTree() {
		root = new Node(null, null);
		size = 1;
	}
	
	public void expandExternal(Position<T> p) {
		if (isInternal(p)) throw new InvalidPositionException("Node is not external");
		
		Node node = (Node) p;
		
		node.left = new Node(null, node);
		node.right = new Node(null, node);
		size+=2;
	}
	
	public void remove(Position<T> p) {
		Node node = (Node) p;
		
		if (isInternal(node.left) && isInternal(node.right))
			throw new InvalidPositionException("Node to be removed has 2 internal children");
		
		if (isExternal(node.left)) {
			if (isRoot(node)) {
				root = node.right;
				node.right.parent = null;
			} else {
				if (left(parent(node)) == node) {
					node.parent.left = node.right;
					node.right.parent = node.parent;
				} else {
					node.parent.right = node.right;
					node.right.parent = node.parent;
				}
			}
		} else {
			if (isRoot(node)) {
				root = node.left;
				node.left.parent = null;
			} else {
				if (left(parent(node)) == node) {
					node.parent.left = node.left;
					node.left.parent = node.parent;
				} else {
					node.parent.right = node.left;
					node.left.parent = node.parent;
				}
			}
		}
		node.parent = null;
		node.right = null;
		node.left = null;
		size -= 2;
	}
}

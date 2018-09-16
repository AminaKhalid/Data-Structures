package dsaii.tree;

import java.util.Iterator;

import dsaii.common.Position;
import dsaii.list.LinkedList;
import dsaii.list.List;

public abstract class AbstractBinaryTree<T> implements BinaryTree<T> {
	@Override
	public Iterator<Position<T>> children(Position<T> p) {
		List<Position<T>> children = new LinkedList<Position<T>>();
		if (hasLeft(p)) children.insertLast(left(p));
		if (hasRight(p)) children.insertLast(right(p));
		return children.iterator();
	}

	@Override
	public Iterator<Position<T>> positions() {
		List<Position<T>> list = new LinkedList<Position<T>>();
		visitPositions(root(), list);
		return list.iterator();
	}
	
	private void visitPositions(Position<T> node, List<Position<T>> list) {
		if (node == null) return;
		
		list.insertLast(node);
		if (hasLeft(node)) visitPositions(left(node), list);
		if (hasRight(node)) visitPositions(right(node), list);
	}

	@Override
	public Iterator<T> iterator() {
		List<T> list = new LinkedList<T>();
		visitPositions2(root(), list);
		return list.iterator();
	}
	
	private void visitPositions2(Position<T> node, List<T> list) {
		list.insertLast(node.element());
		if (hasLeft(node)) visitPositions2(left(node), list);
		if (hasRight(node)) visitPositions2(right(node), list);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		if (isEmpty()) {
			buf.append("Empty Tree");
		} else {
			buildString(root(), "", buf);
		}
		return buf.toString();
	}
	
	private void buildString(Position<T> p, String offset, StringBuffer buf) {
		buf.append(offset);
		buf.append(p.element());
		buf.append("\n");
		if (hasLeft(p)) {
			buildString(left(p), offset+"\t", buf);
		} else {
			buf.append(offset).append("\t-\n");
		}
		
		if (hasRight(p)) {
			buildString(right(p), offset+"\t", buf);
		} else {
			buf.append(offset).append("\t-\n");
		}
	}

}

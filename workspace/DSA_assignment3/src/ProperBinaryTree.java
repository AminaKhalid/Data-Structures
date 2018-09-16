public class ProperBinaryTree<T> extends AbstractBinaryTree<T> {
	public ProperBinaryTree() 
	{
		//part1

		//set size to one
		size = 1;
		//create new node and assign to root
		root = new Node(null, null);

	}

	public void expandExternal(Position<T> p) throws InvalidPositionException
	{
		//part2

		Node node = (Node) p;
		//if p is internal, then throw exception
		if (!isExternal(p)) 
			throw new InvalidPositionException("Node is not external");

		//create new nodes and assign to left and right nodes
		node.left = new Node(null, node);
		node.right = new Node(null, node);

		//increase size by 2
		size += 2;

	}

	public void remove(Position<T> p) 
	{
		//part 3

		Node node = (Node) p;

		//throw exception if both children are internal
		if (isInternal(left(p)) && isInternal(right(p)))
		{
			throw new InvalidPositionException("Both children are internal");
		}

		if (isExternal (left(p)))
			//we will keep the right child
		{
			//removing root node
			if(isRoot(node))
			{
				root = node.right;
				node.right.parent = null;
			}
			else
			{
				if(node.parent.left == node)
				{
					node.parent.left = node.right;
				}
				else
				{
					node.parent.right = node.right;
				}
				node.right.parent = node.parent;
			}
		}
		else
			//we will keep the left child
		{
			//removing root node
			if (isRoot(node))
			{
				root = node.left;
				node.left.parent = null;
			} 
			else 
			{
				if (node.parent.left == node) 
				{
					node.parent.left = node.left;
				} 
				else 
				{
					node.parent.right = node.left;
				}
				node.left.parent = node.parent;
			}
		} 

		//decrease size
		size -= 1;
	}
}
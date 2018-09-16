

public class PBTest2 {
	
	public static void main(String args[])
	{
		//creating a new ProperBinaryTree object
		ProperBinaryTree<String> tree = new ProperBinaryTree<String>();
		
		//expand root node and print result
		tree.expandExternal(tree.root());
		System.out.println(tree.toString());
		
	}

}

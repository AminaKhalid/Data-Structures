package dsaii.tree;

public class ProperTreeTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}
	
	public static void test1() {
		System.out.println("===================== TEST 1 =====================");
		ProperBinaryTree<String> tree = createTestTree();	
		System.out.println(tree);
		tree.remove(tree.root());
		System.out.println(tree);
	}
	
	public static void test2() {
		System.out.println("===================== TEST 2 =====================");
		ProperBinaryTree<String> tree = createTestTree();	
		System.out.println(tree);
		try {
			tree.remove(tree.right(tree.root()));
			System.out.println(tree);
		} catch (RuntimeException e) {
			System.out.println("Expected Result! no updated tree printed");
		}
	}
	
	public static void test3() {
		System.out.println("===================== TEST 3 =====================");
		ProperBinaryTree<String> tree = createTestTree();	
		System.out.println(tree);
		tree.remove(tree.left(tree.right(tree.root())));
		System.out.println(tree);
	}
	
	public static ProperBinaryTree<String> createTestTree() {
		ProperBinaryTree<String> tree = new ProperBinaryTree<String>();
		tree.expandExternal(tree.root());
		tree.replace(tree.root(), "A");
		tree.expandExternal(tree.right(tree.root()));
		tree.replace(tree.right(tree.root()), "B");
		tree.expandExternal(tree.left(tree.right(tree.root())));
		tree.replace(tree.left(tree.right(tree.root())), "C");
		tree.expandExternal(tree.right(tree.right(tree.root())));
		tree.replace(tree.right(tree.right(tree.root())), "D");
		return tree;
	}
}

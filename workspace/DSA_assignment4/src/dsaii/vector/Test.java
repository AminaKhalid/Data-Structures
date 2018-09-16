package dsaii.vector;

public class Test {
	public static void main(String[] args) {
		Vector<String> vector = new ArrayVector<String>();
		vector.insertAtRank(0, "P");
		vector.insertAtRank(0, "H");
		vector.insertAtRank(2, "Y");
		vector.insertAtRank(1, "P");
		vector.insertAtRank(1, "A");
		System.out.println(vector);
	}
}

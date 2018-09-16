package dsaii.vector;

public interface Vector<T> {
	public T elemAtRank(int rank);
	public T replaceAtRank(int rank, T element);
	public void insertAtRank(int rank, T element);
	public T removeAtRank(int rank);
	
	public int size();
	public boolean isEmpty();
}

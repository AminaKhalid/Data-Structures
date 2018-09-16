package dsaii.sequence;

import dsaii.common.Position;
import dsaii.list.List;
import dsaii.vector.Vector;

public interface Sequence<T> extends Vector<T>, List<T> {
	public Position<T> atRank(int rank);
	public int rankOf(Position<T> p);
}

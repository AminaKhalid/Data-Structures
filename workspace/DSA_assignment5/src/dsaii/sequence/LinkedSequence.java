package dsaii.sequence;

import dsaii.common.Position;
import dsaii.list.LinkedList;
import dsaii.vector.RankOutOfBoundsException;

public class LinkedSequence<T> extends LinkedList<T> implements Sequence<T> {
	@Override
	public T elemAtRank(int rank) {
		if (rank < 0 || rank > size-1) {
			throw new RankOutOfBoundsException();
		}
		
		return atRank(rank).element();
	}

	@Override
	public T replaceAtRank(int rank, T element) {
		if (rank < 0 || rank > size-1) {
			throw new RankOutOfBoundsException();
		}
		
		return this.replace(atRank(rank), element);
	}

	@Override
	public void insertAtRank(int rank, T element) {
		if (rank < 0 || rank >= size-1) {
			throw new RankOutOfBoundsException();
		}
		
		insertBefore(atRank(rank), element);
		
	}

	@Override
	public T removeAtRank(int rank) {
		if (rank < 0 || rank > size-1) {
			throw new RankOutOfBoundsException();
		}
		
		return remove(atRank(rank));
	}

    public Position<T> atRank(int rank) {
    	Position<T> current = first();
    	for (int i=0;i<rank;i++) {
    		current = next(current);
    	}
    	return current;
    }

    public int rankOf(Position<T> p) {
    	Position<T> current = first();
    	int i=0;
    	while (current != p) {
    		current = next(current);
    		i++;
    	}
    	return i;
    }
}

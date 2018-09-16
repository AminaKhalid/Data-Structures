package dsaii.common;

import java.util.Random;

import dsaii.sequence.LinkedSequence;
import dsaii.sequence.Sequence;
import dsaii.tree.Heap;

public class SortUtils {
	private static Random random = new Random();

	public static void main(String[] args) {
		Sequence<Integer> list = createSequence(new LinkedSequence<Integer>());
		heapSort(list, true);
		System.out.println("heapSort (asc): " + list);
		list = createSequence(new LinkedSequence<Integer>());
		heapSort(list, false);
		System.out.println("heapSort (desc): " + list);
		list = createSequence(new LinkedSequence<Integer>());
		ascQuickSort(list);
		System.out.println("ascQuickSort: " + list);
		list = createSequence(new LinkedSequence<Integer>());
		ascMergeSort(list);
		System.out.println("ascMergeSort: " + list);
		list = createSequence(new LinkedSequence<Integer>());
		mergeSort(list, true);
		System.out.println("mergeSort (asc): " + list);
		list = createSequence(new LinkedSequence<Integer>());
		mergeSort(list, false);
		System.out.println("mergeSort (desc): " + list);
		list = createSequence(new LinkedSequence<Integer>());
		quickSort(list, true);
		System.out.println("quickSort (asc): " + list);
		list = createSequence(new LinkedSequence<Integer>());
		quickSort(list, false);
		System.out.println("quickSort (desc): " + list);
	}

	/**
	 * We create a sequence rather than a list because sequences can be both rank and position
	 * based. This means we can use the most appropriate sequence implementation for the given
	 * sorting algorithm.
	 * @param list
	 * @return
	 */
	private static Sequence<Integer> createSequence(Sequence<Integer> list) {
		for (int i : new int[] {12, 5, 7, 19, 13, 11, 6, 8, 2, 4, 25, 21, 14} ) {
			list.insertLast(i);
		}
		return list;
	}

	/**
	 * Heap Sort used position based operations, so a LinkedSequence is the best implementation
	 * to use.
	 * 
	 * @param S
	 * @param asc
	 */
	public static <T extends Comparable<T>> void heapSort(Sequence<T> S, boolean asc) {
		Heap<T> heap = new Heap<T>();
		while (!S.isEmpty()) {
			heap.insert(S.remove(S.first()));
		}

		while (!heap.isEmpty()) {
			if (asc) 
				S.insertLast(heap.remove());
			else
				S.insertFirst(heap.remove());
		}
	}

	//Ascend merge sort function
	public static <T extends Comparable<T>> void ascMergeSort(Sequence<T> S) {

		//if the size is less than 2 then return
		if(S.size()<2)
		{
			return;
		}
		else
		{
			//midway is the number halway down the list, 6
			int midway = S.size()/2;
			//create left and right
			Sequence<T> left = new LinkedSequence<>();
			Sequence<T> right = new LinkedSequence<>();

			//to store remaining values
			T element = null;

			//removing the first from S into element
			for(int i=0; i<midway; i++)
			{
				element = S.remove(S.first());
				left.insertLast(element);
			}
			while(!S.isEmpty())
			{
				element = S.remove(S.first());
				right.insertLast(element);
			}

			//recursively call function
			ascMergeSort(left);
			ascMergeSort(right);

			ascend(S, left, right);
		}
	}

	//method for ascend merge sort
	private static <T extends Comparable<T>> void ascend(Sequence<T> S, Sequence<T> left, Sequence<T> right)
	{
		//compare the left element to the right and see if it is less than 0
		while(!left.isEmpty() && !right.isEmpty())
			if(left.first().element().compareTo(right.first().element())<0)
			{
				S.insertLast(left.remove(left.first()));
			}
			else
			{
				S.insertLast(right.remove(right.first()));
			}

		//loop if right and left are not empty to insert
		while(!right.isEmpty())
			S.insertLast(right.remove(right.first()));

		while(!left.isEmpty())
			S.insertLast(left.remove(left.first()));
	}

	//Descend merge sort function
	//same function but we are calling the DESCEND function instead of ASCEND to be used in MergeSort
	public static <T extends Comparable<T>> void descMergeSort(Sequence<T> S) {

		if(S.size()<2)
		{
			return;
		}
		else
		{
			int midway = S.size()/2;
			Sequence<T> left = new LinkedSequence<>();
			Sequence<T> right = new LinkedSequence<>();
			T element = null;

			for(int i=0; i<midway; i++)
			{
				element = S.remove(S.first());
				left.insertLast(element);
			}
			while(!S.isEmpty())
			{
				element = S.remove(S.first());
				right.insertLast(element);
			}

			//call this function recursively
			descMergeSort(left);
			descMergeSort(right);

			descend(S, left, right);

		}
	}

	//method for descend merge sort
	private static <T extends Comparable<T>> void descend(Sequence<T> S, Sequence<T> left, Sequence<T> right)
	{
		//compare the left element to the right and see if it is greater than 0
		while(!left.isEmpty() && !right.isEmpty())
			if(left.first().element().compareTo(right.first().element())>0)
			{
				S.insertLast(left.remove(left.first()));
			}
			else
			{
				S.insertLast(right.remove(right.first()));
			}

		while(!right.isEmpty())
			S.insertLast(right.remove(right.first()));

		while(!left.isEmpty())
			S.insertLast(left.remove(left.first()));
	}



	/**
	 * Merge Sort also used position based operations, so again you should use a LinkedSequence
	 * @param S
	 * @param asc
	 */
	public static <T extends Comparable<T>> void mergeSort(Sequence<T> S, boolean asc) {

		//if asc is true then ascend merge sort
		if(asc){
			ascMergeSort(S);
		}
		//else go to the descMergeSort function
		else{
			descMergeSort(S);
		}
	}

	/**
	 * 
	 * @param S
	 * @param asc
	 */
	//ascend quick sort method to call function for ascending
	public static <T extends Comparable<T>> void ascQuickSort(Sequence<T> S) {

		//if size is less than 2 then return
		if(S.size()<2)
		{
			return;
		}

		quicksortAscend(S, 0, S.size()-1);

	}

	//ASCEND quicksort
	private static <T extends Comparable<T>> void quicksortAscend(Sequence<T> S, int left, int right)
	{
		//if left is less than right
		if(left < right) 
		{
			int p = partition(S, left, right);
			//call quicksortAscend function recusively
			quicksortAscend(S, left, p - 1);
			quicksortAscend(S, p + 1, right);

		}
	}

	//partition method
	private static  <T extends Comparable<T>> int partition(Sequence<T> S, int first, int last)
	{
		//declare pivot value
		T pivot =  S.elemAtRank(last);
		int s = first - 1;

		for(int i=first; i<last; i++)
		{
			//compare the element to the pivot value
			if(S.elemAtRank(i).compareTo(pivot)<=0)
			{
				s++;
				//go to swap method for swapping elements according to whether its greater than or less than the pivot value
				swap(s, i, S);
			}
		}

		swap(last, s+1, S);
		return s+1;

	}

	//descend quick sort method to call function for descending 
	public static <T extends Comparable<T>> void descQuickSort(Sequence<T> S) {

		if(S.size()<2)
		{
			return;
		}

		quicksortDescend(S, 0, S.size()-1);

	}

	//DESCEND quicksort
	private static <T extends Comparable<T>> void quicksortDescend(Sequence<T> S, int left, int right)
	{
		if(left < right) {

			int part = partitionDesc(S, left, right);
			//call quicksortDescend function recursively
			quicksortDescend(S, left, part - 1);
			quicksortDescend(S, part + 1, right);

		}
	}

	//partition method for descend
	private static  <T extends Comparable<T>> int partitionDesc(Sequence<T> S, int first, int last)
	{
		T pivot =  S.elemAtRank(last);
		int s = first - 1;

		for(int i=first; i<last; i++)
		{
			//compare the value with the pivot value (changed to >= for reversing the order)
			if(S.elemAtRank(i).compareTo(pivot)>=0)
			{
				s++;
				swap(s, i, S);
			}
		}

		swap(last, s+1, S);
		return s+1;

	}

	//swapping elements
	private static  <T extends Comparable<T>> void swap(int j, int i, Sequence<T> S)
	{
		T tmp = S.elemAtRank(j);

		S.replaceAtRank(j, S.elemAtRank(i));
		S.replaceAtRank(i, tmp);
	}

	/**
	 * 
	 * @param S
	 * @param asc
	 */
	public static <T extends Comparable<T>> void quickSort(Sequence<T> S, boolean asc) {

		//if asc is true then go to the ascQuickSort method
		if(asc)
		{
			ascQuickSort(S);
		}
		else
		//else go to the descQuickSort method
		{
			descQuickSort(S);
		}
	}
}

package dsaii.list;

import dsaii.common.Position;

public class ArrayListTest1 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		Position<String> position = list.insertFirst("sat");
		list.insertLast("on");
		list.insertLast("the");
		list.insertFirst("cat");
		list.insertLast("mat");
		list.insertFirst("The");
		
		System.out.println(list);
		System.out.println(position);
	}
}

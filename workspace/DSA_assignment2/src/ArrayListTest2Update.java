
public class ArrayListTest2Update {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		Position<String> position = list.insertFirst("sat");
		list.insertAfter(position, "mat,");
		list.insertAfter(position, "on");
		list.insertBefore(position, "really");
		list.insertBefore(list.last(), "the");
		list.insertFirst("The");
		list.insertAfter(list.first(),"cat");
		list.insertLast("of");
		position = list.insertAfter(list.next(list.next(list.next(position))),"what");
		list.insertBefore(list.last(), "you");
		list.insertAfter(list.last(),"that?");
		list.insertBefore(list.prev(list.last()), "think");
		list.insertAfter(position, "do");
		list.replace(list.next(list.next(list.next(list.next(list.next(list.next(list.first())))))), "hat,");
		
		System.out.println(list);
	}
}

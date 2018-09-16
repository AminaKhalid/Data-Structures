import dsaii.stack.LinkedStack;
import dsaii.stack.Stack;

public class Reverser {
	public static void main(String[] args) {
		Stack<String> stack = new LinkedStack<String>();
		stack.push("H");
		stack.push("A");
		stack.push("P");
		stack.push("P");
		stack.push("Y");
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}

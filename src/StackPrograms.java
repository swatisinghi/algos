import java.util.Scanner;
import java.util.Stack;


public class StackPrograms {
	
	
	public boolean validParanthesis(String str) {
		Stack<Character> stack = new Stack<Character>();
		
		for (int i = 0; i < str.length(); i++) {
			char bracket = str.charAt(i);
			switch(bracket) {
				case '(':
				case '[':
				case '{':
					stack.push(str.charAt(i));
					break;
				case '}':
					if (stack.peek() == '{')
						stack.pop();
					else
						return false;
					break;
				case ']':
					if (stack.peek() == '[')
						stack.pop();
					else
						return false;
					break;
				case ')':
					if (stack.peek() == '(')
						stack.pop();
					else
						return false;
					break;
				default: System.out.println("Invalid paranethesis");
					return false;
			}
		}
		return true;
		
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		StackPrograms sp = new StackPrograms();
		
		while (true) {
			System.out.println("Menu");
			System.out.println("1. Valid Paranthesis");
			System.out.println("Q. Exit");
			
			String ch = in.next();
			if (ch.equals("1")) {
				System.out.println("Enter the string");
				System.out.println("Valid: " + sp.validParanthesis(in.next()));
			} else if (ch.equals("Q")) {
				System.exit(0);
			}
		}
	}

}

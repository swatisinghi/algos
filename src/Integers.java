import java.util.Scanner;


public class Integers {
	
	private void reverseInteger(int n) {
		int revNum = 0;
		while (n != 0) {
			int r = n % 10;
			n /= 10;
			revNum = revNum * 10 + r;
		}
		System.out.println("Reversed string: " + revNum);
	}
	
	private void atoi(String str) {
		int num = 0;
		int multiplier = 1;
		if (str.charAt(0) == '-') {
			str = str.substring(1, str.length());
			multiplier = -1;
		}
		for (int i = 0; i < str.length(); i++) {
			num = num * 10 + str.charAt(i) - '0';
		}
		System.out.println(num * multiplier);
	}
	
	private String perm(String str) {
		if (str.length() == 1) 
			return str;
		
		for (int i = 0; i < str.length(); i++) {
			String s = String.valueOf(str.charAt(i)).concat(perm(str.substring(1)));
			System.out.print(s + ", ");
			return s;
		}
		return null;
		
	}
	
	public static void main(String args[]) {
		
		Scanner in = new Scanner(System.in);
		Integers inte = new Integers();
		
		while (true) {
			System.out.println("Menu");
			System.out.println("1. Reverse Integer");
			System.out.println("2. String to integer (atoi)");
			System.out.println("3. Permutation of numbers");
			System.out.println("Q. Exit");
			
			String ch = in.next();
			if (ch.equals("1")) {
				System.out.println("Enter integer");
				inte.reverseInteger(in.nextInt());
			} else if (ch.equals("2")) {
				System.out.println("Enter string");
				inte.atoi(in.next());
			} else if (ch.equals("3")) {
				System.out.println("Enter string");
				inte.perm(in.next());
			} else if (ch.equals("Q")) {
				System.exit(0);
			}
		}
	}

}

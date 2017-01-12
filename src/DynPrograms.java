import java.util.Scanner;


public class DynPrograms {
	
	public DynPrograms() {
		
	}
	
	private int minCutPal(String str) {
		int len = str.length();
		int[][] cuts = new int[len][len];
		int[][] pal = new int[len][len];
		int i, j, k, l;
		
		for (i = 0; i < len; i++) {
			pal[i][i] = 1;
			cuts[i][i] = 0;
		}
		
		for (l = 2; l <= len; l++) {
			for (i = 0; i < len - l + 1; i++) {
				j = i + l - 1;
				
				if (l == 2) {
					pal[i][j] = str.charAt(i) == str.charAt(j) ? 1 : 0;
				} else {
					pal[i][j] = (str.charAt(i) == str.charAt(j)) && pal[i + 1][j - 1] == 1 ? 1 : 0;
				}
				if (pal[i][j] == 1) {
					cuts[i][j] = 0;
				} else {
					cuts[i][j] = 99999;
					for (k = i; k < j; k++) {
						cuts[i][j] = Math.min(cuts[i][j], cuts[i][k] + cuts[k + 1][j] + 1); 
					}
				}
			}
		}
				
		for (i = 0; i < len; i++) {
			for (j = 0; j < len; j++) {
				System.out.print(pal[i][j] + " ");
			}
			System.out.println();
		}
		
		for (i = 0; i < len; i++) {
			for (j = 0; j < len; j++) {
				System.out.print(cuts[i][j] + " ");
			}
			System.out.println();
		}
		return cuts[0][len - 1];
	}
	
	private int optimalStrategy(int[] arr, int[][] tab, int l, int h) {
		if (tab[l][h] != -1) 
			return tab[l][h];
				
		for (int i = l; i <= h; i++) {
			for (int j = i + 2; j <= h; j++) {
				tab[i][j] = Math.max((arr[i] + Math.min(optimalStrategy(arr, tab, i + 1, j - 1), optimalStrategy(arr, tab, i + 2, j))), 
						arr[j] + Math.min(optimalStrategy(arr, tab, i + 1, j - 1), optimalStrategy(arr, tab, i, j - 2)));
			}
		}
		return tab[l][h];
	}
	
	private String longestPalinSubstr(String str) {
		int start = 0;
		int end = 0;
		
		int len = str.length();
		boolean[][] palin = new boolean[len][len];
		for (int i = 0; i < len; i++) {
			palin[i][i] = true;
		}
		
		for (int i = 0; i < len - 1; i++) {
			if (str.charAt(i) == str.charAt(i + 1)) {
				palin[i][i + 1] = true;	
			}
		}
		
		for (int l = 2; l < len; l++) {
			for (int i = 0; i < len - l; i++) {
				int j = i + l;
				if (str.charAt(i) == str.charAt(j) && palin[i + 1][j - 1]) {
					palin[i][j] = true;
					if ((j - i) > (start - end))
						start = i; end = j;
				}
			}
		}
		
		return str.substring(start, end + 1);
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		DynPrograms dyn = new DynPrograms();
		
		while (true) {
			System.out.println("Menu");
			System.out.println("1. Min number of cuts needed for all substrings to be palindrome");
			System.out.println("2. Optimal strategy in a game");
			System.out.println("3. Longest palindromic substring");
			System.out.println("Q. Exit");
			
			String ch = in.next();
			
			if (ch.equals("1")) {
				System.out.println("Enter the string: ");
				String str = in.next();
				System.out.println(dyn.minCutPal(str));
			} if (ch.equals("2")) {
				System.out.println("Enter number of coins: ");
				int n = in.nextInt();
				System.out.println("Enter the values of the coins: ");
				int[] arr = new int[n];
				for (int i = 0; i < n; i++) {
					arr[i] = in.nextInt();
				}
				int[][] tab = new int[n][n];
				
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (i == j) {
							tab[i][j] = arr[i];
						} else if (j == i + 1) {
							tab[i][j] = Math.max(arr[i], arr[j]);
						} else {
							tab[i][j] = -1;
						}
					}
				}
				dyn.optimalStrategy(arr, tab, 0, n - 1);
				System.out.println(tab[0][n - 1]);
			} else if (ch.equals("3")) {
				System.out.println("Enter the string: ");
				String str = in.next();
				System.out.println(dyn.longestPalinSubstr(str));
			} else if (ch.equals("Q")) {
				System.exit(0);
			}
		}
	}
}

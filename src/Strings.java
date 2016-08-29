import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * Created by swati on 2/7/16.
 */
public class Strings {

	Scanner in;
    public Strings() {
    	this.in = new Scanner(System.in);
    }

    private int countWords(String fileLoc) {
        String currLine;
        BufferedReader br = null;
        int words = 0;
        try {
            br = new BufferedReader(new FileReader(fileLoc));
            while ((currLine = br.readLine()) != null) {
                words += currLine.split(" ").length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return words;
    }
    
    private void longestSubstring(String str, int l) {
    	ArrayList<String> result = new ArrayList<String>();
    	int maxLength = 0;
    	for (int j = 0; j < str.length() - l; j++) {
    		String s = Character.toString(str.charAt(j));
    		int k = l - 1;
        	int i = j + 1;
    		while (i < str.length() && k != 0) {
        		if (str.charAt(i - 1) == str.charAt(i)) {
        			s = s.concat(Character.toString(str.charAt(i)));    			
        		} else if (str.charAt(i - 1) != str.charAt(i)) {
        			s = s.concat(Character.toString(str.charAt(i)));
        			k = k - 1;
        		}
        		i++;
        	}
        	while (i < str.length()) {
        		if (str.charAt(i - 1) == str.charAt(i)) {
        			s = s.concat(Character.toString(str.charAt(i)));
        		} else {
        			break;
        		}
        		i++;
        	}
        	System.out.println(s);
    	}
    	
    }

    public static void main(String args[]) {

        while (true) {
            System.out.println("Sorting Algos");
            System.out.println("1. Count words in a file");
            System.out.println("2. Find the longest substring with k unique characters in a given string");
            System.out.println("Q. Exit");
            System.out.println("Enter your choice: ");

            Strings str = new Strings();
            Scanner in = new Scanner(System.in);
            String ch = in.next();
            if (ch.equals("1")) {
                System.out.println("Enter the file location: ");
                System.out.println("Number of words are: " + str.countWords(in.next()));
            } else if (ch.equals("2")) {
            	System.out.println("Enter the string: ");
            	String s = in.next();
            	System.out.println("Enter k: ");
            	int k = in.nextInt();
            	System.out.println("Result: ");
            	str.longestSubstring(s, k);
            }
        }
    }
}

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
    
    private String rle(String str) {
    	System.out.println(str);
    	String rleStr = "";
    	for (int i = 0; i < str.length();) {
    		rleStr = rleStr.concat(Character.toString(str.charAt(i)));
    		int charCount = 1;
    		int j = i + 1;
    		while (j < str.length()) {
	    		if (str.charAt(i) == str.charAt(j)) {
	    			charCount += 1;
	    		} else {
	    			break;
	    		}
	    		j++;
    		}
    		i += charCount;
    		rleStr = rleStr.concat(Integer.toString(charCount));
    	}
    	System.out.println("===== " + rleStr);
    	return rleStr;
    }
    
    private char[] inPlaceRle (char[] str) {
    	//aaaabbccc
    	
    	int i = 0;
    	while (str[i] != '\0') {
    		int charCount = 1;
    		int j = i + 1;
	    	while (str[j] != '\0') {
	    		if (str[i] == str[j]) {
	    			charCount++;
	    		} else {
	    			break;
	    		}
	    		j++;
	    	}
	    	str[i + 1] = (char) ('0' + charCount);
	    	int k = i + 2;
	    	System.out.println(str.length);
	    	while (j < str.length) {
	    		if (str[j] == '\0')
	    			break;
	    		else {
		    		System.out.println("K: " + k + " - " + str[k]);
		    		System.out.println("J: " + j + " - " + str[j]);
		    		str[k++] = str[j];
	    		}
	    		j++;
	    	}
	    	str[k] = '\0';
	    	System.out.println("======== ");
	    	i += 2;
    	}
    	return str;
    }
    
    public static boolean matchRegex(String str, String regex) {
    	if (regex.length() == 0)
			return str.length() == 0;
    	if (regex.charAt(0) == '.') {
    		if (str.length() >= 1) 
    			return matchRegex(str.substring(1), regex.substring(1));
    		else
    			return false;
		}
    	if (regex.charAt(0) == '*') {
    		if (str.length() == 0) 
    			return matchRegex(str, regex.substring(1));
    		else	
    			return matchRegex(str.substring(1), regex.substring(0));
    	} 
    	if (str.charAt(0) == regex.charAt(0)) {
    		return matchRegex(str.substring(1), regex.substring(1));
    	} else {
    		if (regex.length() > 1 && regex.charAt(1) == '*') 
    			return matchRegex(str.substring(0), regex.substring(2));
    	}
		return false;
		   
	}

    public static void main(String args[]) {

        while (true) {
            System.out.println("Sorting Algos");
            System.out.println("1. Count words in a file");
            System.out.println("2. Find the longest substring with k unique characters in a given string");
            System.out.println("3. Run length encoding");
            System.out.println("4. In place Run length encoding");
            System.out.println("5. Regex Match");
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
            } else if (ch.equals("3")) {
                System.out.println("Enter the string: ");
                System.out.println(str.rle(in.next()));
            } else if (ch.equals("4")) {
                System.out.println("Enter the string: ");
                char[] res = str.inPlaceRle(in.next().toCharArray());
                System.out.println("Result: ");
                for (int i = 0; i < res.length; i++) {
                	System.out.println(res[i]);
                }
            } else if (ch.equals("5")) {
            	System.out.println("Enter the string: ");
            	String inputStr = in.next();
            	System.out.println("Enter the regex string: ");
            	String regexStr = in.next();
            	System.out.println("Result: " + str.matchRegex(inputStr, regexStr));
            } else if (ch.equals("Q")) {
                System.exit(0);
            }
        }
    }
}

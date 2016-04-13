import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;

/**
 * Created by swati on 2/7/16.
 */
public class Strings {

    public Strings() {

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

    public static void main(String args[]) {

        while (true) {
            System.out.println("Sorting Algos");
            System.out.println("1. Count words in a file");
            System.out.println("Q. Exit");
            System.out.println("Enter your choice: ");

            Strings str = new Strings();
            Scanner in = new Scanner(System.in);
            String ch = in.next();
            if (ch.equals("1")) {
                System.out.println("Enter the file location: ");
                System.out.println("Number of words are: " + str.countWords(in.next()));
            }
        }
    }
}

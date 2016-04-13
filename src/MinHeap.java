import java.util.Scanner;

/**
 * Created by swati on 1/15/16.
 */
public class MinHeap {

    int arr[];
    int size;
    Scanner in;
    int currSize;

    public MinHeap(int size) {
        this.arr = new int[size];
        this.size = size;
        this.in = new Scanner(System.in);
        this.currSize = -1;
    }

    private int parent(int n) {
        return (n - 1) /2;
    }

    private int left(int n) {
        return 2 * n + 1;
    }

    private int right(int n) {
        return 2 * n + 2;
    }

    private void insert(int n) {
        currSize = currSize + 1;
        arr[currSize] = n;

        int itr = currSize;
        while (itr != 0 && arr[parent(itr)] > n) {
            int parent = parent(itr);
            int temp = arr[itr];
            arr[itr] = arr[parent];
            arr[parent] = temp;
            itr = parent;
        }
    }

    private void create() {
        System.out.println("Enter the numbers: ");
        for (int i = 0 ; i < size; i++) {
            arr[i] = in.nextInt();
        }


        buildMinHeap();
    }

    private void minHeapify(int i) {
        int min = i;
        int left = left(i);
        int right = right(i);

        if (left <= size && arr[left] < arr[i]) {
            min = left;
        } else if (right <= size && arr[right] < arr[i]) {
            min = right;
        }

        if (i != min) {
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            minHeapify(min);
        }
    }

    private void buildMinHeap() {
        for (int i = size/2 - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }
    private void print() {

        for (int i = 0; i <= size/2 - 1; i++) {
            System.out.println("Root: " + arr[i] + ", Left: " + arr[left(i)] + ", Right: " + arr[right(i)]);
        }
    }

    public static void main(String args[]) {
        System.out.println("Enter the size: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        MinHeap minHeap = new MinHeap(n);

        while (true) {
            System.out.println("Menu");
            System.out.println("1. Insert one by one");
            System.out.println("2. Insert all");
            System.out.println("3. Print");
            System.out.println("Q. Quit");

            String ch = in.next();

            if (ch.equals("1")) {
                System.out.println("Enter the number");
                minHeap.insert(in.nextInt());
            } else if (ch.equals("2")) {
                minHeap.create();
            } else if (ch.equals("3")) {
                minHeap.print();
            } else if (ch.equals("Q")) {
                System.exit(0);
            }
        }

    }

}

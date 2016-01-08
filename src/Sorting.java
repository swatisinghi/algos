import java.util.Scanner;

public class Sorting {

    private Scanner in;
    private int size;
    private int[] arr;

    public Sorting(int size, int[] arr) {
        this.in = new Scanner(System.in);
        this.size = size;
        this.arr = arr;
    }

    private void printArray() {
        System.out.println("Sorted Array: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private void bubbleSort() {
        System.out.println("Bubble Sorting...");
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        printArray();
    }

    private void insertionSort() {
        System.out.println("Insertion Sorting...");
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        printArray();

    }

    private void selectionSort() {
        System.out.println("Selection Sorting...");
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        printArray();
    }

    private void merge(int low, int mid, int high) {
        int[] sortedArr = new int[size];
        int i = low;
        int j = low, k = mid + 1;
        while (j <= mid && k <= high) {
            if (arr[j] < arr[k]) {
                sortedArr[i++] = arr[j++];
            } else {
                sortedArr[i++] = arr[k++];
            }
        }
        while (j <= mid) {
            sortedArr[i++] = arr[j++];
        }
        while (k <= high) {
            sortedArr[i++] = arr[k++];
        }
        for (int m = low; m < i; m++) {
            arr[m] = sortedArr[m];
        }
    }

    private void mergeSort(int low, int high) {
        if (low >= high)
            return;
        int mid = (low + high) / 2;
        mergeSort(low, mid);
        mergeSort(mid + 1, high);
        merge(low, mid, high);
    }

    private int partition(int low, int high) {
        int i = low, j = low;
        int p = arr[high];
        while (j < high) {
            if (arr[j] < p) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
            j++;
        }
        int temp = arr[high];
        arr[high] = arr[i];
        arr[i] = temp;
        return i;
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int p = partition(low, high);
            quickSort(low, p -1);
            quickSort(p + 1, high);
        }
    }

    public static void main(String args[]) {

        while(true) {
            System.out.println("Sorting Algos");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Insertion Sort");
            System.out.println("3. Selection Sort");
            System.out.println("4. Merge Sort");
            System.out.println("5. Quick Sort");
            System.out.println("Q. Exit");
            System.out.println("Enter your choice: ");

            Scanner in = new Scanner(System.in);
            String ch = in.next();

            System.out.println("Enter size of the array: ");
            int n = in.nextInt();

            System.out.println("Enter the Array");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }

            System.out.println("The Array is:");
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();

            Sorting sort = new Sorting(n, arr);
            if (ch.equals("1")) {
                sort.bubbleSort();
            } else if (ch.equals("2")) {
                sort.insertionSort();
            } else if (ch.equals("3")) {
                sort.selectionSort();
            } else if (ch.equals("4")) {
                System.out.println("Merge Sorting...");
                sort.mergeSort(0, n - 1);
                sort.printArray();
            } else if (ch.equals("5")) {
                System.out.println("Quick Sorting...");
                sort.quickSort(0, n - 1);
                sort.printArray();
            } else {
                System.exit(0);
            }
        }
    }
}

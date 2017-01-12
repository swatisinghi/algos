import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by swati on 1/18/16.
 */
public class Arrays {

    private int size;
    private int[] arr;
    Scanner in;

    public  Arrays(int size) {
        this.in = new Scanner(System.in);
        this.size = size;
        this.arr = new int[size];
    }

    private void create() {
        System.out.println("Enter the " + size + " numbers: ");
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }
    }

    private void divide(int n) {
        int i = 0, j = 0, k = size - 1;
        while (j < size && j <= k) {
            if (arr[j] < n && i == j) {
                i++;
                j++;
            } else if (arr[j] == n) {
                j++;
            } else if (arr[k] > n) {
                k--;
            } else if (arr[j] < n) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            } else if (arr[j] > n) {
                int temp = arr[k];
                arr[k] = arr[j];
                arr[j] = temp;
                k--;
            }
        }
    }

    private void print() {

        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

    private void findPair(int sum) {
        boolean[] map = new boolean[100000];
        for (int i = 0; i < arr.length; i++) {
            int temp = sum - arr[i];
            if (temp >= 0 && map[temp] == true) {
                System.out.println("Pair found: " + arr[i] + " and " + (sum - arr[i]));
                break;
            }
            map[arr[i]] = true;
        }
    }

    private int binarySearch(int low, int high, int k) {
        int found = -1;
        if (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == k) {
                return mid;
            } else if (arr[mid] > k) {
                found = binarySearch(low, mid - 1, k);
            } else {
                found = binarySearch(mid + 1, high, k);
            }
        }
        return found;
    }

    private int findFloorCieling(int low, int high, int k) {
        int pos = -1;
        if (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == k || (mid == 0 || arr[mid] < k) && arr[mid + 1] > k) {
                return mid;
            } else if (arr[mid] > k) {
                pos = findFloorCieling(low, mid - 1, k);
            } else {
                pos = findFloorCieling(mid + 1, high, k);
            }
        }
        return pos;
    }
    
    private int smallestNumGreaterThan(int low, int high, int k) {
    	int found = -1;
    	if (low <= high) {
    		int mid = (low + high) / 2;
    		if (arr[mid] == k) {
    			return arr[mid + 1];
    		}
    		else if (k < arr[mid]) {
    			found = smallestNumGreaterThan(low, mid - 1, k);
    		} else {
    			found = smallestNumGreaterThan(mid + 1, high, k);
    		}
    	}
    	return found;
    }
    
    private boolean possibleTraingle(int i, int j, int k) {
    	return (i + j > k && i + k > j && j + k > i);
    }
    
    private void countTriangles() {
    	int k = size - 1;
    	int i = 0;
    	while (i < k) {
    		for (int j = i + 1; j < k; j++) {
	    		while (k > j) {
	    			if (possibleTraingle(arr[i], arr[j], arr[k])) {
	        			System.out.println(arr[i] + ", " + arr[j] + ", " + arr[k]);
	        		}
	    			k--;
	    		}
	    		k = size - 1;
    		}
    		i++;
    		
    	}
    }
    
    private int arrangeBricks(int n) {
    	
    	if (n == 1) {
    		return 1;
    	} else if (n == 2) {
    		return 2;
    	} else {
    		return arrangeBricks(n -1) + arrangeBricks(n - 2);
    	}
    	
    }

    private void menu() {
        while (true) {

            System.out.println("Menu");
            System.out.println("1. Create");
            System.out.println("2. Divide into 3 parts based on k");
            System.out.println("3. Find 2 numbers that add up to k");
            System.out.println("4. Floor and Cieling in a sorted array");
            System.out.println("5. Binary Search");
            System.out.println("6. Find the smallest number greater than k in a sorted array");
            System.out.println("7. Count the number of possible triangles");
            System.out.println("8. Given a infinite number of bricks of size 1x 2. How many arrangements can be made for wall size 2 x N.");
            System.out.println("Q. Quit");

            String ch = in.next();

            if (ch.equals("1")) {
                create();
            } else if (ch.equals("2")) {
                System.out.println("Enter k: ");
                int k = in.nextInt();
                divide(k);
                print();
            } else if (ch.equals("3")) {
                System.out.println("Enter k: ");
                findPair(in.nextInt());
            } else if (ch.equals("4")) {
                System.out.println("Enter k: ");
                int k = in.nextInt();
                int floor = -1, cieling = -1;
                if (k < arr[0]) {
                    floor = -1;
                    cieling = arr[0];
                } else if (arr[size - 1] < k) {
                    floor = arr[size - 1];
                    cieling = -1;
                } else {
                    int pos = findFloorCieling(0, size - 1, k);
                    if (arr[pos] == k) {
                        floor = k;
                        cieling = k;
                    } else if (pos != -1) {
                        floor = arr[pos];
                        cieling = arr[pos + 1];
                    }
                }

                System.out.println("floor: " + floor + ", cieling: " + cieling);
            } else if (ch.equals("5")) {
                System.out.println("Enter k: ");
                System.out.println(binarySearch(0, size - 1, in.nextInt()));
            } else if (ch.equals("6")) {
                System.out.println("Enter k: ");
                int res = smallestNumGreaterThan(0, size - 1, in.nextInt());
                System.out.println("Result: " + res);
            } else if (ch.equals("7")) {
                countTriangles();
            } else if (ch.equals("8")) {
            	System.out.println("Enter n: ");
            	System.out.println("Result: " + arrangeBricks(in.nextInt()));
            } else if (ch.equals("Q")) {
                System.exit(0);
            }
        }

    }

    public static void main(String args[]) {
        System.out.println("Enter the size: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        Arrays a = new Arrays(n);
        a.menu();

    }
}

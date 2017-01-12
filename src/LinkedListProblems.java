import java.util.ArrayList;
import java.util.Scanner;


public class LinkedListProblems {
	
	Scanner in = new Scanner(System.in);
	
	public class Node {
		private int data;
		private Node next;
		
		public Node(int n) {
			data = n;
			next = null;
		}
		
		private int getData() {
			return this.data;
		}
		
		private Node getNext() {
			return this.next;
		}
		
		private void setNext(Node node) {
			this.next = node;
		}
	}
	
	private Node createList() {
		System.out.println("Enter number of elements");
		int n = in.nextInt();
		System.out.println("Enter elements");
		Node head = new Node(in.nextInt());
		Node curr = head;
		for (int i = 1; i < n; i++) {
			Node node = new Node(in.nextInt());
			curr.setNext(node);
			curr = node;
		}
		
		return head;
	}
	
	private void printList(Node node) {
		Node curr = node;
		while (curr != null) {
			System.out.print(curr.getData() + " -> ");
			curr = curr.getNext();
		}
	}
	
	private Node merge(Node first, Node second) {
		
		if (first == null) return second;
		if (second == null) return first;
		
		if (first.getData() < second.getData()) {
			first.next = merge(first.getNext(), second);
			return first;
		}
		else {
			second.next = merge(first, second.getNext());
			return second;
		}
		
	}
	
	private void mergeLists(Node[] lists, int k) {
		System.out.println("Swati.......");
		while (k != 0) {
			int start = 0;
			int end = k;
			
			while (start < end) {	
				System.out.println("Merging below 2 lists..");
				printList(lists[start]);
				printList(lists[end]);
				Node mergeList = merge(lists[start], lists[end]);
				System.out.println("Swati....... after merge");
				printList(mergeList);
				lists[start] = mergeList;
				start++;
				end--;
				
				if (start >= end)
					k = end;
			}
		}
	}
	
	
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		LinkedListProblems llp = new LinkedListProblems();
		
		while (true) {
			System.out.println("Menu");
			System.out.println("1. Merge K sorted lists");
			System.out.println("Q. Exit");
			
			String ch = in.next();
			if (ch.equals("1")) {
				System.out.println("Enter number of lists");
				int num = in.nextInt();
				Node[] lists = new Node[num];
				
				for (int i = 0; i < num; i++) {
					System.out.println("Enter list: " + i);
					lists[i] = llp.createList();
					
				}
				
				for (int i = 0; i < num; i++) {
					System.out.print("Printing list: " + i + " - ");
					llp.printList(lists[i]);
					System.out.println();
				}
				
				llp.mergeLists(lists, num - 1);
				System.out.print("Printing Merged list");
				llp.printList(lists[0]);
				
			} else if (ch.equals("Q")) {
				System.exit(0);
			}
		}
	}

}

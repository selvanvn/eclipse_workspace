package day15;

import java.util.Scanner;

public class Solution {

	public static Node insert(Node head, int data) {
		Node n = new Node(data);
		Node temp = head;
		if (head == null) {
			head = n;
			return head;
		}
		while (temp.next != null) {
			temp = temp.next;
			System.out.println("I'm here");
		}
		temp.next = n;
		return head;
	}

	public static void display(Node head) {
		Node start = head;
		while (start != null) {
			System.out.print(start.data + " ");
			start = start.next;
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Node head = null;
		int N = sc.nextInt();

		while (N-- > 0) {
			System.out.println("Here" +N);
			//System.out.println("While" +N);
			int ele = sc.nextInt();
			head = insert(head, ele);
			
		}
		display(head);
		sc.close();
	}
}

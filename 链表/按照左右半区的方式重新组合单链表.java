package chapter_2_listproblem;

//假设链表长度为n，时间复杂度为o(n),空间为o(1)
public class Problem_20_RelocateLinkedList {

	public static class Node {
		public int value;
		public Node next;

		public Node(int value) {
			this.value = value;
		}
	}

	// 如果链表为空或者长度为1，不用调整，直接结束
	// 链表长度大于1，遍历一遍找到左半区的最后一个节点，为mid
	// 遍历一遍找到mid之后，将左半区与右半区分离成两个链表，分别为left和right
	// 将两个链表合并起来
	public static void relocate(Node head) {
		if (head == null || head.next == null) {
			return;
		}
		Node mid = head;
		Node right = head.next;
		while (right.next != null && right.next.next != null) {
			mid = mid.next;
			right = right.next.next;
		}
		right = mid.next;
		mid.next = null;
		mergeLR(head, right);
	}

	public static void mergeLR(Node left, Node right) {
		Node next = null;
		while (left.next != null) {
			next = right.next;
			right.next = left.next;
			left.next = right;
			left = right.next;
			right = next;
		}
		left.next = right;
	}

	public static void printLinkedList(Node head) {
		System.out.print("Linked List: ");
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = null;
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.next = new Node(7);
		relocate(head);
		printLinkedList(head);

	}

}

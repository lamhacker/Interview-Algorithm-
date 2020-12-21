package chapter_2_listproblem;

public class Problem_05_ReversePartList {

	// 给定一个单向链表头节点head，和两个整数from和to，
	// 在单向链表上把第from个节点到第to个节点这一部分进行反转
	// 1->2->3->4->5->null, from=2, to=4
	// 1->4->3->2->5->null
	// 再如 1->2->3->null, from=1, to=3
	// 3->2->1->null
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}
	// 时间复杂度为o(n), 额外空间复杂度要求为o(1)
	// 因为有可能会换头，所以函数应该返回调整后的新头节点
	// 先判断是否满足1 <= from <= to <= n,如果不满足的话直接返回原来的头节点
	// 找到第from-1个节点fpre和第to+1个几点tpos,fpre就是要反转部分的前一个节点,
	// tpos是反转部分的后一个节点，把反转的部分先反转，然后正确地连接fpre和tpos
	// 比如1->2->3->4->null，假设fpre为节点1，tpos为节点4，要反转部分为2->3
	// 先反转成3->2，然后fpre连向节点3，节点2连向tpos，就变成了1->3->2->4->null
	// 如果fpre为null，说明反转部分是包含头节点的，那么就返回新的头节点，也就是没反转之前
	// 反转部分的最后一个节点，也是反转之后反转部分的第一个节点，如果fpre不为null，那么就返回旧的头节点
	public static Node reversePart(Node head, int from, int to) {
		int len = 0;
		Node node1 = head;
		Node fPre = null;
		Node tPos = null;
		while (node1 != null) {
			len++;
			fPre = len == from - 1 ? node1 : fPre;
			tPos = len == to + 1 ? node1 : tPos;
			node1 = node1.next;
		}
		if (from > to || from < 1 || to > len) {
			return head;
		}
		node1 = fPre == null ? head : fPre.next;
		Node node2 = node1.next;
		node1.next = tPos;
		Node next = null;
		while (node2 != tPos) {
			next = node2.next;
			node2.next = node1;
			node1 = node2;
			node2 = next;
		}
		if (fPre != null) {
			fPre.next = node1;
			return head;
		}
		return node1;
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
		printLinkedList(head);
		head = reversePart(head, 1, 1);
		printLinkedList(head);

		head = new Node(1);
		printLinkedList(head);
		head = reversePart(head, 1, 1);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		printLinkedList(head);
		head = reversePart(head, 1, 2);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		head = reversePart(head, 2, 3);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		head = reversePart(head, 1, 3);
		printLinkedList(head);

	}

}

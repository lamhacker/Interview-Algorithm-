package chapter_2_listproblem;

import java.util.Stack;

// 假设链表中每一个节点的值都在0-9之间，那么链表整体就可以代表一个整数
// 比如9->3->7,可以代表整数937
// 给定两个这种链表的头节点head1和head2，生成代表两个整数相加值的结果链表
// 比如链表为9->3->7, 6->3，最后新的结果链表为1->0->0->0
public class Problem_10_AddTwoLinkedLists {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// 使用栈
	// 将2个链表分别从左到右遍历，遍历过程将值压栈，这样就生成了两个链表节点值的逆序栈，
	// 分别表示为s1和s2
	// 将两个链表从低位到高位依次弹出，在这个过程中生成相加链表就可以，
	// 同时需要关注每一步是否有进位，用那个ca表示
	// 当s1和s2都为空时，还要关注一下进位信息是否为1，如果为1，表示还要
	// 生成一个节点值为1的新节点，记为new4，然后让new4.next=new3
	// 然后返回新生成的结果链表就可以了
	public static Node addLists1(Node head1, Node head2) {
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
		while (head1 != null) {
			s1.push(head1.value);
			head1 = head1.next;
		}
		while (head2 != null) {
			s2.push(head2.value);
			head2 = head2.next;
		}
		int ca = 0;
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		Node node = null;
		Node pre = null;
		while (!s1.isEmpty() || !s2.isEmpty()) {
			n1 = s1.isEmpty() ? 0 : s1.pop();
			n2 = s2.isEmpty() ? 0 : s2.pop();
			n = n1 + n2 + ca;
			pre = node;
			node = new Node(n % 10);
			node.next = pre;
			ca = n / 10;
		}
		if (ca == 1) {
			pre = node;
			node = new Node(1);
			node.next = pre;
		}
		return node;
	}

	// 用了链表的逆序，省掉了栈的空间
	// 就是将链表逆序，这样就可以得到从低位到高位的数组
	// 同步遍历两个逆序的链表，这样就可以得到两个链表从低位到高位的数字
	// 然后在这个过程中生成相加链表，还有需要用ca看每一步是否有进位。
	// 当两个链表都遍历完成后，还要看进位是不是位1，
	// 如果为1的话还要生成一个节点值为1的新节点
	// 返回新生成的结果链表
	public static Node addLists2(Node head1, Node head2) {
		head1 = reverseList(head1);
		head2 = reverseList(head2);
		int ca = 0;
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		Node c1 = head1;
		Node c2 = head2;
		Node node = null;
		Node pre = null;
		while (c1 != null || c2 != null) {
			n1 = c1 != null ? c1.value : 0;
			n2 = c2 != null ? c2.value : 0;
			n = n1 + n2 + ca;
			pre = node;
			node = new Node(n % 10);
			node.next = pre;
			ca = n / 10;
			c1 = c1 != null ? c1.next : null;
			c2 = c2 != null ? c2.next : null;
		}
		if (ca == 1) {
			pre = node;
			node = new Node(1);
			node.next = pre;
		}
		reverseList(head1);
		reverseList(head2);
		return node;
	}

	public static Node reverseList(Node head) {
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
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
		Node head1 = new Node(9);
		head1.next = new Node(9);
		head1.next.next = new Node(9);

		Node head2 = new Node(1);

		printLinkedList(head1);
		printLinkedList(head2);
		printLinkedList(addLists1(head1, head2));
		printLinkedList(addLists2(head1, head2));

	}

}

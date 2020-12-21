package chapter_2_listproblem;

// 给定两个有序链表头节点head1和head2，合并变成有序
public class Problem_19_MergeTwoLinkedLists {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// 链表长度为m和n，那么时间复杂度为o(m+n),空间复杂度为o(1)
	// 如果链表有一个为空，那就不需要合并，返回另一个链表头节点就可以了
	// 然后比较head1和head2的值，小的节点也是合并后链表的最小值，这个节点是合并链表的头节点head
	// 然后哪个链表的头节点更小，另一个链表的所有节点都会依次插入到这个链表中
	// 设head节点所在的链表为链表1，另一个链表为链表2，链表1和链表2都从头部
	// 开始一起遍历，比较每次遍历到的两个节点的值，cur1和cur2，然后根据大小关系做出不同的调整
	// 同时桶一个变量pre表示上次比较时值比较小的节点
	// 然后如果链表1先走完，那么cur1=null, pre为链表1的最后一个节点，那么就把pre的next指针指向链表2当前的节点，
	// 表示把链表2没遍历到有序部分直接拼接到最后，调整结束，如果链表2先走完，说明链表2的所有节点都已经插入到链表1中，调整结束
	// 返回合并后链表的头节点head
	public static Node merge(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return head1 != null ? head1 : head2;
		}
		Node head = head1.value < head2.value ? head1 : head2;
		Node cur1 = head == head1 ? head1 : head2;
		Node cur2 = head == head1 ? head2 : head1;
		Node pre = null;
		Node next = null;
		while (cur1 != null && cur2 != null) {
			if (cur1.value <= cur2.value) {
				pre = cur1;
				cur1 = cur1.next;
			} else {
				next = cur2.next;
				pre.next = cur2;
				cur2.next = cur1;
				pre = cur2;
				cur2 = next;
			}
		}
		pre.next = cur1 == null ? cur2 : cur1;
		return head;
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

		Node head1 = null;
		Node head2 = null;
		Node head = merge(head1, head2);
		printLinkedList(head);

		head1 = new Node(1);
		head2 = null;
		head = merge(head1, head2);
		printLinkedList(head);

		head1 = null;
		head2 = new Node(1);
		head = merge(head1, head2);
		printLinkedList(head);

		head1 = new Node(1);
		head2 = new Node(2);
		head = merge(head1, head2);
		printLinkedList(head);

		head1 = new Node(2);
		head2 = new Node(1);
		head = merge(head1, head2);
		printLinkedList(head);

		head1 = new Node(1);
		head1.next = new Node(4);
		head2 = new Node(2);
		head2.next = new Node(3);
		head2.next.next = new Node(5);
		head = merge(head1, head2);
		printLinkedList(head);

		head1 = new Node(1);
		head1.next = new Node(3);
		head1.next.next = new Node(5);
		head1.next.next.next = new Node(7);
		head1.next.next.next.next = new Node(9);
		head2 = new Node(0);
		head2.next = new Node(6);
		head2.next.next = new Node(6);
		head2.next.next.next = new Node(7);
		head = merge(head1, head2);
		printLinkedList(head);

	}

}

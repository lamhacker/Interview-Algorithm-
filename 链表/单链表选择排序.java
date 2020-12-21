package chapter_2_listproblem;

// 给定一个无序单链表的头节点head，实现单链表的选择排序
// 空间为o(1)
public class Problem_16_ListSelectionSort {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}
	// 时间o(n^2),空间o(1)
	// 开始的时候默认整个链表都是没有排序的部分，对于找到第一个最小值节点，
	// 肯定是整个链表的最小值节点，将其设置为新的头节点记为newhead
	// 每次在未排序的部分中找到最小值的节点，然后把这个节点从没有排序的链表中删除
	// 删除的过程要保证没有排序的部分的链表在结构上不会断开，比如2->1->3,删除节点1之后，链表应该
	// 变为2->3，需要找到删除节点的前一个节点
	// 然后把删除的节点也就是每次的最小值节点连接到排好序部分的链表尾部
	// 全部做完返回newhead
	public static Node selectionSort(Node head) {
		Node tail = null; // sorted part tail
		Node cur = head; // unsorted part head
		Node smallPre = null; // previous node of the smallest node
		Node small = null; // smallest node
		while (cur != null) {
			small = cur;
			smallPre = getSmallestPreNode(cur);
			if (smallPre != null) {
				small = smallPre.next;
				smallPre.next = small.next;
			}
			cur = cur == small ? cur.next : cur;
			if (tail == null) {
				head = small;
			} else {
				tail.next = small;
			}
			tail = small;
		}
		return head;
	}

	public static Node getSmallestPreNode(Node head) {
		Node smallPre = null;
		Node small = head;
		Node pre = head;
		Node cur = head.next;
		while (cur != null) {
			if (cur.value < small.value) {
				smallPre = pre;
				small = cur;
			}
			pre = cur;
			cur = cur.next;
		}
		return smallPre;
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
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(1);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(2);
		head.next = new Node(1);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(3);
		head.next.next = new Node(2);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(2);
		head.next = new Node(1);
		head.next.next = new Node(3);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(2);
		head.next = new Node(3);
		head.next.next = new Node(1);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(3);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(3);
		head.next = new Node(2);
		head.next.next = new Node(1);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(3);
		head.next = new Node(1);
		head.next.next = new Node(4);
		head.next.next.next = new Node(2);
		head = selectionSort(head);
		printLinkedList(head);

	}

}

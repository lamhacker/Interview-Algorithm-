package chapter_2_listproblem;

import java.util.HashMap;

public class Problem_09_CopyListWithRandom {
	// 给定一个由node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表中所有结构的复制
	// 并且返回复制的新链表的头节点 比如链表1->2->3->null,假设1的rand指针指向3，2的rand指针指向null，
	// 3的rand指针指向1。复制后的链表应该也是这种结构，比如1'->2'->3'->null, 1'的rand指针指向3'，2'的rand指针
	// 指向null，3‘的rand指针指向1’，最后返回1'
	public static class Node {
		public int value;
		public Node next;
		public Node rand;

		public Node(int data) {
			this.value = data;
		}
	}
	// 时间o(n),空间o(n)
	// 使用了哈希表。就是从左到右遍历链表，对每个节点都复制生成相应的副本节点，然后将对应关系
	// 放入哈希表map，比如链表1->2->3->null，遍历1，2，3时依次生成1‘，2’，3‘，最后将对应关系放入map
	// 相当于每个副本节点的next和rand指针
	// 然后将1’节点作为结果返回
	// 哈希表增删改查的操作时间为o(1)，一共只遍历链表2遍，所以为o(n)
	// 因为使用了哈希表来保存原节点和副本节点的对应关系，所以额外空间复杂度为o(n)
	public static Node copyListWithRand1(Node head) {
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		while (cur != null) {
			map.get(cur).next = map.get(cur.next);
			map.get(cur).rand = map.get(cur.rand);
			cur = cur.next;
		}
		return map.get(head);
	}

	// o(n) 空间为o(1)
	// 首先从左到右遍历链表，对每个节点cur都复制生成相应的副本节点copy，
	// 然后把copy放在cur和下一个要遍历节点的中间
	/// 再从到右遍历链表，在遍历时设置每一个副本节点的rand指针
	// 然后因为所有的节点和副本节点串在一起，将它分离出来就可以了
	//最后就讲1‘节点作为结果返回
	public static Node copyListWithRand2(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		Node next = null;
		// copy node and link to every node
		while (cur != null) {
			next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		Node curCopy = null;
		// set copy node rand
		while (cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			curCopy.rand = cur.rand != null ? cur.rand.next : null;
			cur = next;
		}
		Node res = head.next;
		cur = head;
		// split
		while (cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			cur.next = next;
			curCopy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
	}

	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = null;
		Node res1 = null;
		Node res2 = null;
		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

	}

}

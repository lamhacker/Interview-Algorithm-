package chapter_2_listproblem;

import java.util.HashSet;


// 给定一个无序单链表的头节点head，删除其中值重复出现的节点
public class Problem_13_RemoveRepetition {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}
	// 时间为o(n), 空间为o(n)
	//使用hashset。
	//首先因为头节点不用删除节点，所以首先将头节点的值放入hashset里面
	// 从头节点的下一个节点开始往后遍历节点，假设当前遍历到cur节点，先检查cur的值
	// 是否在hashset里面，如果在那就说明cur节点的值是之前出现过的，就将cur节点删除，
	// 删除的方式是将最近一个没有被删除的节点pre连接到cur的下一个节点，就是pre.next=cur.next
	// 如果不在，将cur节点的值加入哈希表，同时让pre=cur，就是更新最近一个没有被删除的节点
	public static void removeRep1(Node head) {
		if (head == null) {
			return;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		Node pre = head;
		Node cur = head.next;
		set.add(head.value);
		while (cur != null) {
			if (set.contains(cur.value)) {
				pre.next = cur.next;
			} else {
				set.add(cur.value);
				pre = cur;
			}
			cur = cur.next;
		}
	}

	//时间为o(n^2）, 空间为o(1)
	// 类似选择排序
	// 链表为1->2->3->3->4->4->2->1>1>null
	// 首先是头节点，节点值为1，往后检查所有值为1的节点，全部删除，链表变为
	// 1->2->3->3->4->4->2->null
	// 然后是第二个节点，节点值为2，往后检查所有值为2的节点，全部删除。链表变为：
	// 1->2->3->4->4->null
	// 最后是第四个节点，节点值为4，往后检查所有值为4的节点，全部删除，链表变为
	// 1->2->3->4->null
	// 结束
	public static void removeRep2(Node head) {
		Node cur = head;
		Node pre = null;
		Node next = null;
		while (cur != null) {
			pre = cur;
			next = cur.next;
			while (next != null) {
				if (cur.value == next.value) {
					pre.next = next.next;
				} else {
					pre = next;
				}
				next = next.next;
			}
			cur = cur.next;
		}
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
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(4);
		head.next.next.next.next.next = new Node(4);
		head.next.next.next.next.next.next = new Node(2);
		head.next.next.next.next.next.next.next = new Node(1);
		head.next.next.next.next.next.next.next.next = new Node(1);
		removeRep1(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(1);
		head.next.next = new Node(3);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(4);
		head.next.next.next.next.next = new Node(4);
		head.next.next.next.next.next.next = new Node(2);
		head.next.next.next.next.next.next.next = new Node(1);
		head.next.next.next.next.next.next.next.next = new Node(1);
		removeRep2(head);
		printLinkedList(head);

	}

}

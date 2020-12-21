package chapter_2_listproblem;

import java.util.Stack;

// 给定一个链表的头节点head和一个整数num，请实现函数将值num的节点全部删除
// 链表为1->2->3->4->null, num=3,链表调整后为：1->2->4->null
public class Problem_14_RemoveGivenValue {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// 用栈做，时间为o(n),空间为o(n)
	// 将值不等于num的节点用栈收集起来，收集完成后重新连接就可以。
	// 最后将栈底的节点作为新的头节点返回
	public static Node removeValue1(Node head, int num) {
		Stack<Node> stack = new Stack<Node>();
		while (head != null) {
			if (head.value != num) {
				stack.push(head);
			}
			head = head.next;
		}
		while (!stack.isEmpty()) {
			stack.peek().next = head;
			head = stack.pop();
		}
		return head;
	}

	// 不用任何容器而直接调整的方法
	// 时间o(n),空间o(1)
	// 首先从链表头开始，找到第一个值不等于num的节点，作为新的头节点，
	// 这个节点是不用删除，记为newhead，继续往后遍历，假设当前节点为cur，
	// 如果cur节点值等于num,就将cur节点删除，删除的方式是将之前最近一个值不等于num
	// 的节点pre连接到cur的下一个节点，也就是pre.next=cur.next。如果cur节点不等于num,
	//就让pre=cur，就是更新最近一个值不等于num的节点
	public static Node removeValue2(Node head, int num) {
		while (head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		Node pre = head;
		Node cur = head;
		while (cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
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
		Node head = new Node(1);
		head.next = new Node(1);
		head.next.next = new Node(3);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(1);
		head.next.next.next.next.next = new Node(2);
		head.next.next.next.next.next.next = new Node(1);
		head.next.next.next.next.next.next.next = new Node(1);
		head = removeValue1(head, 1);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(1);
		head.next.next = new Node(3);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(1);
		head.next.next.next.next.next = new Node(2);
		head.next.next.next.next.next.next = new Node(1);
		head.next.next.next.next.next.next.next = new Node(1);
		head = removeValue2(head, 1);
		printLinkedList(head);

	}

}

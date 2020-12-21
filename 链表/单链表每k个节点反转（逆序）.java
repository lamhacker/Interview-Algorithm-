package chapter_2_listproblem;

import java.util.Stack;

// 给定一个单链表的头节点head，实现一个调整单链表的函数，使得每个k个节点
// 之间的逆序，如果最后不够k个节点一组，那么就不调整最后几个节点
// 1->2->3->4->5->6->7->8->null, k=3
// 3->2->1->6->5->4->7->8->null 
public class Problem_12_ConvertEveryKNodesInList {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// 首先如果k的值小于2，那不用进行任何调整，因为k<1没有意义，k==1
	// 的时候代表每1个节点为1组进行逆序，链表不会有任何变化。

	// 然后用栈做的话时间复杂度为o(n), 空间复杂度为o(k)
	// 从左到右遍历链表，如果栈大小不等于k就将节点不断压入栈中，
	// 当栈大小第一次到达k的时候，说明第一次凑齐了k个节点进行逆序，
    // 从栈中依次弹出这些节点，并根据弹出的顺序重新连接，这一组逆序完成后，需要记录一下新的头部
    // 同时第一组的最后一个节点应该连接下一个节点
    // 当栈的大小每次到达k的时候，说明又凑齐了一组应该进行逆序的节点，从栈中
    // 依次弹出这些节点，并根据弹出的顺序重新连接。逆序完成后，那组的第一个节点应该被
    // 上一组的最后一个节点连接上，这一组的最后一个节点应该连接下一个节点，然后继续去
    // 凑下一组，直到链表都被遍历万
    //最后应该返回newhead,作为链表希的头节点
	public static Node reverseKNodes1(Node head, int K) {
		if (K < 2) {
			return head;
		}
		Stack<Node> stack = new Stack<Node>();
		Node newHead = head;
		Node cur = head;
		Node pre = null;
		Node next = null;
		while (cur != null) {
			next = cur.next;
			stack.push(cur);
			if (stack.size() == K) {
				pre = resign1(stack, pre, next);
				newHead = newHead == head ? cur : newHead;
			}
			cur = next;
		}
		return newHead;
	}

	public static Node resign1(Stack<Node> stack, Node left, Node right) {
		Node cur = stack.pop();
		if (left != null) {
			left.next = cur;
		}
		Node next = null;
		while (!stack.isEmpty()) {
			next = stack.pop();
			cur.next = next;
			cur = next;
		}
		cur.next = right;
		return cur;
	}

	// 不需要栈结构，在链表中直接调整
	// 用变量记录每一组开始的第一个节点和最后一个节点，然后直接逆序调整
	// 把这一组的节点都逆序。和方法一样，然后每个组在逆序重连之后需要让那个组第一个节点被之前组的最后一个节点连接上，
	// 将那个组的最后一个节点连接下一个节点
	public static Node reverseKNodes2(Node head, int K) {
		if (K < 2) {
			return head;
		}
		Node cur = head;
		Node start = null;
		Node pre = null;
		Node next = null;
		int count = 1;
		while (cur != null) {
			next = cur.next;
			if (count == K) {
				start = pre == null ? head : pre.next;
				head = pre == null ? cur : head;
				resign2(pre, start, cur, next);
				pre = start;
				count = 0;
			}
			count++;
			cur = next;
		}
		return head;
	}

	public static void resign2(Node left, Node start, Node end, Node right) {
		Node pre = start;
		Node cur = start.next;
		Node next = null;
		while (cur != right) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		if (left != null) {
			left.next = end;
		}
		start.next = right;
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
		int K = 3;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

		head = new Node(1);
		K = 3;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

		head = new Node(1);
		head.next = new Node(2);
		K = 2;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

		head = new Node(1);
		head.next = new Node(2);
		K = 3;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		K = 2;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.next = new Node(7);
		head.next.next.next.next.next.next.next = new Node(8);
		K = 3;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

	}

}

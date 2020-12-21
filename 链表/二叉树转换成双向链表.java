package chapter_2_listproblem;

import java.util.LinkedList;
import java.util.Queue;

//将一个搜索二叉树转换成一个有序的双向链表
public class Problem_15_BSTtoDoubleLinkedList {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 用队列收集二叉树中序遍历结果时间o(n)，空间o(n)
	// 生成一个队列queue，然后用中序遍历将每个节点放入queue
	// 从queue中依次弹出节点，并按照弹出的顺序重连所有的节点
	public static Node convert1(Node head) {
		Queue<Node> queue = new LinkedList<Node>();
		inOrderToQueue(head, queue);
		if (queue.isEmpty()) {
			return head;
		}
		head = queue.poll();
		Node pre = head;
		pre.left = null;
		Node cur = null;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			pre.right = cur;
			cur.left = pre;
			pre = cur;
		}
		pre.right = null;
		return head;
	}

	public static void inOrderToQueue(Node head, Queue<Node> queue) {
		if (head == null) {
			return;
		}
		inOrderToQueue(head.left, queue);
		queue.offer(head);
		inOrderToQueue(head.right, queue);
	}


	public static class RetrunType {
		public Node start;
		public Node end;

		public RetrunType(Node start, Node end) {
			this.start = start;
			this.end = end;
		}
	}
	
	// 利用递归函数，时间为o(n),空间为o(二叉树的高度)
	// process会处理所有的子树，子树的数量就是二叉树节点的个数。所以时间复杂度为o(n)
	// process递归函数最多占用二叉树高度为h的栈空间，所以额外空间复杂度为o(h)
	// 实现递归函数process将一颗搜索二叉树转换为一个有序双向链表，
	// 尾节点的right指针指向双向链表的头节点，process最后返回这个链表的尾节点
	public static Node convert2(Node head) {
		if (head == null) {
			return null;
		}
		return process(head).start;
	}

	// 先用process处理左子树，就将左子树转换了有序双向链表，
	// 同时返回尾节点，记为lefte,再用process函数处理右子树，
	// 就将右子树转换成有序双向链表，同时返回尾节点righte
	// 然后把左子树process处理后的返回节点的right指针连下。

	// 为什么要将有序双向链表的尾节点连接头节点之后再返回尾节点呢？因为这样
	// 可以快速找到双向链表的头尾两端，从而省去了通过遍历过程才能找到两端的麻烦
	public static RetrunType process(Node head) {
		if (head == null) {
			return new RetrunType(null, null);
		}
		RetrunType leftList = process(head.left);
		RetrunType rightList = process(head.right);
		if (leftList.end != null) {
			leftList.end.right = head;
		}
		head.left = leftList.end;
		head.right = rightList.start;
		if (rightList.start != null) {
			rightList.start.left = head;
		}
		return new RetrunType(leftList.start != null ? leftList.start : head,
				rightList.end != null ? rightList.end : head);
	}

	public static void printBSTInOrder(Node head) {
		System.out.print("BST in-order: ");
		if (head != null) {
			inOrderPrint(head);
		}
		System.out.println();
	}

	public static void inOrderPrint(Node head) {
		if (head == null) {
			return;
		}
		inOrderPrint(head.left);
		System.out.print(head.value + " ");
		inOrderPrint(head.right);
	}

	public static void printDoubleLinkedList(Node head) {
		System.out.print("Double Linked List: ");
		Node end = null;
		while (head != null) {
			System.out.print(head.value + " ");
			end = head;
			head = head.right;
		}
		System.out.print("| ");
		while (end != null) {
			System.out.print(end.value + " ");
			end = end.left;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(2);
		head.right = new Node(9);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.left.right.right = new Node(4);
		head.right.left = new Node(7);
		head.right.right = new Node(10);
		head.left.left = new Node(1);
		head.right.left.left = new Node(6);
		head.right.left.right = new Node(8);

		printBSTInOrder(head);
		head = convert1(head);
		printDoubleLinkedList(head);

		head = new Node(5);
		head.left = new Node(2);
		head.right = new Node(9);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.left.right.right = new Node(4);
		head.right.left = new Node(7);
		head.right.right = new Node(10);
		head.left.left = new Node(1);
		head.right.left.left = new Node(6);
		head.right.left.right = new Node(8);

		printBSTInOrder(head);
		head = convert2(head);
		printDoubleLinkedList(head);

	}

}
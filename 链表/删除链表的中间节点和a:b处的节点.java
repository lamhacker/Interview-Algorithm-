package chapter_2_listproblem;

public class Problem_03_RemoveNodeByRatio {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}
	// 删除链表中间节点
	// 如果链表为空或者长度为1，不需要调整，就直接返回，如果链表长度为2
	// 将头节点删除，当链表长度到达3，应该删除第2个节点，当链表长度为4，应该
	// 删除第2个节点，当链表长度为5，应该删除第3个节点。也就是链表长度每增加
	// 2（3，5，7），要删除的节点就后移一个节点。然后如果要删除一个节点，
	// 就需要找到需要删除节点的前一个节点
	public static Node removeMidNode(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		if (head.next.next == null) {
			return head.next;
		}
		Node pre = head;
		Node cur = head.next.next;
		while (cur.next != null && cur.next.next != null) {
			pre = pre.next;
			cur = cur.next.next;
		}
		pre.next = pre.next.next;
		return head;
	}

	// 删除链表a/b处的节点
	// 先计算double r = double(a * n) / ((double)b)的值，然后向上
	// 取整之后的整数值代表该删除的节点是第几个节点
	// 比如链表长度为7， a = 5, b = 7
	// r = (7 * 5)/7 = 5.0 向上取整后为5，所以应该删除第5个节点
	// 然后知道需要删除第几个节点之后，接下来找到需要删除节点的前一个节点就可以
	public static Node removeByRatio(Node head, int a, int b) {
		if (a < 1 || a > b) {
			return head;
		}
		int n = 0;
		Node cur = head;
		while (cur != null) {
			n++;
			cur = cur.next;
		}
		n = (int) Math.ceil(((double) (a * n)) / (double) b);
		if (n == 1) {
			head = head.next;
		}
		if (n > 1) {
			cur = head;
			while (--n != 1) {
				cur = cur.next;
			}
			cur.next = cur.next.next;
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
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		printLinkedList(head);
		head = removeMidNode(head);
		printLinkedList(head);
		head = removeByRatio(head, 2, 5);
		printLinkedList(head);
		head = removeByRatio(head, 1, 3);
		printLinkedList(head);

	}

}

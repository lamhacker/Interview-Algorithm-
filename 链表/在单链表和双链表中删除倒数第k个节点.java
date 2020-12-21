package chapter_2_listproblem;

public class Problem_02_RemoveLastKthNode {

	//单链表
	public static class Node {
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}


	public static Node removeLastKthNode(Node head, int lastKth) {
		// 如果链表为空或者k小于1，参数是无效的
		if (head == null || lastKth < 1) {
			return head;
		}
		// 让链表从头开始走到尾，每移动一步就让k值减1，
		//当链表到了结尾时候，如果k大于0，如果k值等于0，说明链表倒数第k个节点就是头节点
		//这样的话返回head.next，就是原链表的第二个节点，让第二个节点作为链表的头返回
		//相当于删除头节点
		// 如果k小于0的话，就是重新从头节点开始走，每移动一步，就让k加1，
		//当k等于0，移动停止，移动到的节点就是要删除节点的前一个节点
		// 因为比如说链表长度为n，删除倒数第k个，
		//倒数第k个节点的前一个节点就是n-k个节点。第一次遍历后
		// k的值变为k-n, 第二次遍历时，k的值不断加1，加到0就停止遍历
		// 这样第二次遍历就会停到第n-k个节点
		Node cur = head;
		while (cur != null) {
			lastKth--;
			cur = cur.next;
		}
		if (lastKth == 0) {
			head = head.next;
		}
		if (lastKth < 0) {
			cur = head;
			while (++lastKth != 0) {
				cur = cur.next;
			}
			cur.next = cur.next.next;
		}
		return head;
	}

	// 双链表
	public static class DoubleNode {
		public int value;
		public DoubleNode last;
		public DoubleNode next;

		public DoubleNode(int data) {
			this.value = data;
		}
	}

	// 双链表和单链表一样，注意last指针的重连
	public static DoubleNode removeLastKthNode(DoubleNode head, int lastKth) {
		if (head == null || lastKth < 1) {
			return head;
		}
		DoubleNode cur = head;
		while (cur != null) {
			lastKth--;
			cur = cur.next;
		}
		if (lastKth == 0) {
			head = head.next;
			head.last = null;
		}
		if (lastKth < 0) {
			cur = head;
			while (++lastKth != 0) {
				cur = cur.next;
			}
			DoubleNode newNext = cur.next.next;
			cur.next = newNext;
			if (newNext != null) {
				newNext.last = cur;
			}
		}
		return head;
	}

	// 打印单链表
	public static void printLinkedList(Node head) {
		System.out.print("Linked List: ");
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}

	// 打印双链表
	public static void printDoubleLinkedList(DoubleNode head) {
		System.out.print("Double Linked List: ");
		DoubleNode end = null;
		while (head != null) {
			System.out.print(head.value + " ");
			end = head;
			head = head.next;
		}
		System.out.print("| ");
		while (end != null) {
			System.out.print(end.value + " ");
			end = end.last;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// 单链表情况
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		printLinkedList(head1);
		head1 = removeLastKthNode(head1, 3);
		// head1 = removeLastKthNode(head1, 6);
		// head1 = removeLastKthNode(head1, 7);
		printLinkedList(head1);
        
        // 双链表情况
		DoubleNode head2 = new DoubleNode(1);
		head2.next = new DoubleNode(2);
		head2.next.last = head2;
		head2.next.next = new DoubleNode(3);
		head2.next.next.last = head2.next;
		head2.next.next.next = new DoubleNode(4);
		head2.next.next.next.last = head2.next.next;
		head2.next.next.next.next = new DoubleNode(5);
		head2.next.next.next.next.last = head2.next.next.next;
		head2.next.next.next.next.next = new DoubleNode(6);
		head2.next.next.next.next.next.last = head2.next.next.next.next;
		printDoubleLinkedList(head2);
		head2 = removeLastKthNode(head2, 3);
		// head2 = removeLastKthNode(head2, 6);
		// head2 = removeLastKthNode(head2, 7);
		printDoubleLinkedList(head2);

	}

}

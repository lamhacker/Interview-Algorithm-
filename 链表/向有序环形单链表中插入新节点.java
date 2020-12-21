package chapter_2_listproblem;

public class Problem_18_InsertNumToCircularList {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	//生成节点值为num为新节点，记为node，如果链表为空，让node自己组成环形链表，然后直接返回node
	//如果链表为空，让node自己组成环形链表，然后直接返回node
	// 如果链表不为空，让变量pre=head, cur=head.next， 然后让pre和cur同步移动下去
	// 如果遇到pre的节点小于或等于num，并且cur的节点值大于或等于num。那么node应该在pre节点和cur节点之间插入，
	// 插入node，然后返回head就可以。比如链表1->3->4->1,num=2,应该把节点值为2的节点插入1-3中，然后返回头节点
	// pre和cur转了一圈，没有刚刚说的情况，那么node应该插入到头节点的签名。要么是因为node节点的值比链表中每个节点的值都大，要么是因为node的值比链表中每个节点的值都小。
	// 1->3->4->1->...,num=5, 应该把节点为5的节点，插入节点为1的前面
	// 1->3->4->1->...,num=0, 也应该把节点值为0的节点插入到节点1的前面
	// 如果node节点的值比链表的每个节点的值都大，返回原来的头节点就可以，如果node节点的值比链表中每个节点的值都小，应该把node作为链表新的头节点返回
	public static Node insertNum(Node head, int num) {
		Node node = new Node(num);
		if (head == null) {
			node.next = node;
			return node;
		}
		Node pre = head;
		Node cur = head.next;
		while (cur != head) {
			if (pre.value <= num && cur.value >= num) {
				break;
			}
			pre = cur;
			cur = cur.next;
		}
		pre.next = node;
		node.next = cur;
		return head.value < num ? head : node;
	}

	public static void printCircularList(Node head) {
		if (head == null) {
			return;
		}
		System.out.print("Circular List: " + head.value + " ");
		Node cur = head.next;
		while (cur != head) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println("-> " + head.value);
	}

	public static void main(String[] args) {
		Node head = null;
		head = insertNum(head, 2);
		printCircularList(head);
		head = insertNum(head, 1);
		printCircularList(head);
		head = insertNum(head, 4);
		printCircularList(head);
		head = insertNum(head, 3);
		printCircularList(head);
		head = insertNum(head, 5);
		printCircularList(head);
		head = insertNum(head, 0);
		printCircularList(head);

	}

}

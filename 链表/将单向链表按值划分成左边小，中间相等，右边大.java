package chapter_2_listproblem;


// 给定一个单向链表的头节点head，节点的值类型为整型，再给定一个整数pivot，实现一个
// 调整链表的函数，将链表调整为左部分都是值小于pivot的节点，中间部分都是值等于pivot的节点
// 右部分都是值大于pivot的节点
// 比如链表9->0->4->5->1 pivot=3
// 调整后链表为1->0->4->9->5, 也可以是0->1>9->5->4
public class Problem_08_SmallerEqualBigger {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}
    // 时间复杂度为o(n),额外空间复杂度为o(n)
    // 就是把链表中的所有节点放入一个额外的数组中，然后统一统一调整位置
	// 具体的话先遍历一遍数组，拿数组的长度
	// 生成一个相同的数组长度的node类型数组nodearr, 然后遍历链表，将节点都放进nodearr里
	// 在nodearr中把小雨pivot的节点放在左边，把相等的放中间，把大于的放在右边，改进了快排
	// partition的调整过程，就是arrpartition方法
	// 然后满足节点顺序了话就把nodearr里面的节点依次重联起来
	public static Node listPartition1(Node head, int pivot) {
		if (head == null) {
			return head;
		}
		Node cur = head;
		int i = 0;
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		Node[] nodeArr = new Node[i];
		i = 0;
		cur = head;
		for (i = 0; i != nodeArr.length; i++) {
			nodeArr[i] = cur;
			cur = cur.next;
		}
		arrPartition(nodeArr, pivot);
		for (i = 1; i != nodeArr.length; i++) {
			nodeArr[i - 1].next = nodeArr[i];
		}
		nodeArr[i - 1].next = null;
		return nodeArr[0];
	}
	// 快排的partition
	public static void arrPartition(Node[] nodeArr, int pivot) {
		int small = -1;
		int big = nodeArr.length;
		int index = 0;
		while (index != big) {
			if (nodeArr[index].value < pivot) {
				swap(nodeArr, ++small, index++);
			} else if (nodeArr[index].value == pivot) {
				index++;
			} else {
				swap(nodeArr, --big, index);
			}
		}
	}

	public static void swap(Node[] nodeArr, int a, int b) {
		Node tmp = nodeArr[a];
		nodeArr[a] = nodeArr[b];
		nodeArr[b] = tmp;
	}

	// 在左，中，右三个部分的内部也做顺序要求，
	// 要求每个部分里的节点从左到右顺序和原链表中的节点先后次序一致
	// 比如链表为9->0>4->5>1, pivot = 3, 调整后的链表为0->1->9->4->5

	// 时间复杂度o(n), 空间复杂度为o(1）
	// 将链表的所有节点依次划分进三个链表，三个链表分别为small代表左部分
	// equal代表中间部分，big代表右部分
	// 将small,equal和big三个链表重新串起来就可以了
	// 主要就是对null节点的判断和处理
	public static Node listPartition2(Node head, int pivot) {
		Node sH = null; // small head
		Node sT = null; // small tail
		Node eH = null; // equal head
		Node eT = null; // equal tail
		Node bH = null; // big head
		Node bT = null; // big tail
		Node next = null; // save next node
		// every node distributed to three lists
		while (head != null) {
			next = head.next;
			head.next = null;
			if (head.value < pivot) {
				if (sH == null) {
					sH = head;
					sT = head;
				} else {
					sT.next = head;
					sT = head;
				}
			} else if (head.value == pivot) {
				if (eH == null) {
					eH = head;
					eT = head;
				} else {
					eT.next = head;
					eT = head;
				}
			} else {
				if (bH == null) {
					bH = head;
					bT = head;
				} else {
					bT.next = head;
					bT = head;
				}
			}
			head = next;
		}
		// small and equal reconnect
		if (sT != null) {
			sT.next = eH;
			eT = eT == null ? sT : eT;
		}
		// all reconnect
		if (eT != null) {
			eT.next = bH;
		}
		return sH != null ? sH : eH != null ? eH : bH;
	}

	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		printLinkedList(head1);
		// head1 = listPartition1(head1, 4);
		head1 = listPartition2(head1, 5);
		printLinkedList(head1);

	}

}

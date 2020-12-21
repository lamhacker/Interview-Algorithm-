//时间复杂度：O(n)，其中 n 是链表的节点数量。需要对每个节点进行更新指针的操作。
//空间复杂度：O(n)，其中 n 是链表的节点数量。空间复杂度主要取决于递归调用的栈空间。

//可以通过递归的方式实现两两交换链表中的节点。
//递归的终止条件是链表中没有节点，或者链表中只有一个节点，此时无法进行交换。
//如果链表中至少有两个节点，则在两两交换链表中的节点之后，
//原始链表的头节点变成新的链表的第二个节点，
//原始链表的第二个节点变成新的链表的头节点。链表中的其余节点的两两交换可以递归地实现。
//在对链表中的其余节点递归地两两交换之后，更新节点之间的指针关系，即可完成整个链表的两两交换。
//用 head 表示原始链表的头节点，新的链表的第二个节点，
//用 newHead 表示新的链表的头节点，原始链表的第二个节点，则原始链表中的其余节点的头节点是 newHead.next。令 head.next = swapPairs(newHead.next)，
//表示将其余节点进行两两交换，交换后的新的头节点为 head 的下一个节点。然后令 newHead.next = head，即完成了所有节点的交换。
//最后返回新的链表的头节点 newHead。

class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}

//时间复杂度：O(n)，其中 nn 是链表的节点数量。需要对每个节点进行更新指针的操作。
//空间复杂度：O(1)。
//也可以通过迭代的方式实现两两交换链表中的节点。
//创建哑结点 dummyHead，令 dummyHead.next = head。令 temp 表示当前到达的节点，初始时 temp = dummyHead。每次需要交换 temp 后面的两个节点。
//如果 temp 的后面没有节点或者只有一个节点，则没有更多的节点需要交换，因此结束交换。否则，获得 temp 后面的两个节点 node1 和 node2，通过更新节点的指针关系实现两两交换节点。
//具体而言，交换之前的节点关系是 temp -> node1 -> node2，交换之后的节点关系要变成 temp -> node2 -> node1，因此需要进行如下操作。
//temp.next = node2
//node1.next = node2.next
//node2.next = node1
//完成上述操作之后，节点关系即变成 temp -> node2 -> node1。再令 temp = node1，对链表中的其余节点进行两两交换，直到全部节点都被两两交换。
//两两交换链表中的节点之后，新的链表的头节点是 dummyHead.next，返回新的链表的头节点即可。

class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }
}


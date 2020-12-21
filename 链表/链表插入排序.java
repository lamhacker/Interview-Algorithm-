
// 时间o(n^2), 空间o(1)
//插入排序的基本思想是，维护一个有序序列，初始时有序序列只有一个元素，
//每次将一个新的元素插入到有序序列中，将有序序列的长度增加 11，直到全部元素都加入到有序序列中。

//如果是数组的插入排序，则数组的前面部分是有序序列，
//每次找到有序序列后面的第一个元素（待插入元素）的插入位置，
//将有序序列中的插入位置后面的元素都往后移动一位，然后将待插入元素置于插入位置。
//对于链表而言，插入元素时只要更新相邻节点的指针即可，不需要像数组一样将插入位置后面的元素往后移动，
//因此插入操作的时间复杂度是 O(1)，但是找到插入位置需要遍历链表中的节点，时间复杂度是 O(n)
//因此链表插入排序的总时间复杂度仍然是 O(n^2)

//对链表进行插入排序的具体过程如下。
//首先判断给定的链表是否为空，若为空，则不需要进行排序，直接返回。
//创建哑节点 dummyHead，令 dummyHead.next = head。引入哑节点是为了便于在 head 节点之前插入节点。
//维护 lastSorted 为链表的已排序部分的最后一个节点，初始时 lastSorted = head。
//维护 curr 为待插入的元素，初始时 curr = head.next。
//比较 lastSorted 和 curr 的节点值。
//若 lastSorted.val <= curr.val，说明 curr 应该位于 lastSorted 之后，将 lastSorted 后移一位，curr 变成新的 lastSorted。
//否则，从链表的头节点开始往后遍历链表中的节点，寻找插入 curr 的位置。令 prev 为插入 curr 的位置的前一个节点，进行如下操作，完成对 curr 的插入：
//令 curr = lastSorted.next，此时 curr 为下一个待插入的元素。
//重复刚刚说的步骤，直到 curr 变成空，排序结束。
//返回 dummyHead.next，为排序后的链表的头节点。


class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }
}
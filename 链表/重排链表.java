
//题目
//给定链表1->2->3->4, 重新排列1->4->2->3
//1->2->3->4->5, 重新排列 1->5->2->4->3



//时间复杂度：O(N)，其中 N 是链表中的节点数。
//空间复杂度：O(N)，其中 N 是链表中的节点数。主要为线性表的开销。

//因为链表不支持下标访问，所以我们无法随机访问链表中任意位置的元素。
//利用线性表存储链表，
//然后利用线性表可以下标访问的特点，直接按顺序访问指定元素，重建该链表即可。

class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
}

//可划分为三步
// 找到链表中点（快慢指针o(n)找到链表中间），然后把链表右半端反转（迭代），
//将链表两端合并（因为两链表长度相差不超过1，所以直接合并）
// 时间复杂度o(n)，空间复杂度o(1)
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }
}


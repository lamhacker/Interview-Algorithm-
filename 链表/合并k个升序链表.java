
// 分治合并
// 将k个链表配对并将同一对中的链表合并
// 第一轮合并以后，k个链表被合并成k/2个链表，平均长度为2n/k, 然后是k/4个链表，k/8个链表等
// 重复这个过程，直到得到最终有序链表
// 第一轮合并k/2组链表，每一组时间复杂度为o(2n), 第二轮合并k/4组链表，每一个组时间代价o(4n
// 所以总时间为o(kn * lokk)
// 空间o(logk)
class Solution1 {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }
}

//需要维护当前每个链表没有被合并的元素的最前面一个，
//k个链表就最多有 k 个满足这样条件的元素，
//每次在这些元素里面选取 val 属性最小的元素合并到答案中。
//在选取最小元素的时候，用优先队列来优化这个过程。

//时间复杂度：考虑优先队列中的元素不超过 k个，那么插入和删除的时间代价为 O(logk)，这里最多有 kn 个点，
// 对于每个点都被插入删除各一次，故总的时间代价即渐进时间复杂度为 O(kn * log k)
//空间复杂度：这里用了优先队列，优先队列中的元素不超过 k 个，故渐进空间复杂度为 O(k)。


class Solution2 {
    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }
}


作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


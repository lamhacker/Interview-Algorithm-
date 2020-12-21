//将二叉树展开为单链表之后，
// 单链表中的节点顺序即为二叉树的前序遍历访问各节点的顺序。
// 因此，可以对二叉树进行前序遍历，获得各节点被访问到的顺序。
// 由于将二叉树展开为链表之后会破坏二叉树的结构，
//因此在前序遍历结束之后更新每个节点的左右子节点的信息，将二叉树展开为单链表。

// 前序遍历递归
class Solution {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }
}

// 前序遍历迭代
//时间复杂度：O(n)，其中 n 是二叉树的节点数。前序遍历的时间复杂度是 O(n)，
//前序遍历之后，需要对每个节点更新左右子节点的信息，时间复杂度也是 O(n)。

//空间复杂度：O(n)，其中 n 是二叉树的节点数。
//空间复杂度取决于栈（递归调用栈或者迭代中显性使用的栈）
//和存储前序遍历结果的列表的大小，栈内的元素个数不会超过 n，前序遍历列表中的元素个数是 n。


class Solution {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }
}

// 对于当前节点，如果其左子节点不为空，
//则在其左子树中找到最右边的节点，作为前驱节点，
//将当前节点的右子节点赋给前驱节点的右子节点，
//然后将当前节点的左子节点赋给当前节点的右子节点，
//并将当前节点的左子节点设为空。对当前节点处理结束后，
//继续处理链表中的下一个节点，直到所有节点都处理结束。

// 时间复杂度：O(n)，其中 n 是二叉树的节点数。展开为单链表的过程中，需要对每个节点访问一次，
//在寻找前驱节点的过程中，每个节点最多被额外访问一次。

//空间复杂度：O(1)。


class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }
}







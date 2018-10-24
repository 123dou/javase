package swordToOffer;

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class GetNext {
    public static void main(String[] args) {

    }

    /**
     * 1.该结点有右结点
     * 2.该结点没有右结点:
     * --->1.该结点位于其父结点的左子结点或者该结点为根结点
     * ---->2.该结点位于其父结点的右子结点
     *
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) return pNode;
        if (pNode.right != null) { //1.该结点有右结点
            TreeLinkNode node = pNode.right;
            TreeLinkNode parent = pNode.right;
            while (node != null) {
                parent = node;
                node = node.left;
            }
            return parent;
        } else if (pNode.next != null && pNode == pNode.next.right) { //2.该结点没有右结点且不是根结点
            TreeLinkNode parent = pNode.next;
            TreeLinkNode node = pNode;
            while (parent != null && node == parent.right) {
                node = parent;
                parent = parent.next;
            }
            return parent;
        } else return pNode.next;
    }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}

package algs4.tree.rbTree;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class BinaryTree {
    private Node root;
    private int size;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 前序遍历,递归实现
     *
     * @param root
     */
    public void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 用栈来模拟递归的前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            Node curr = root;
            Stack<Node> stack = new Stack<>();
            stack.push(curr);
            while (!stack.isEmpty()) {
                curr = stack.pop();
                System.out.print(curr.data + " ");
                if (curr.right != null) stack.push(curr.right);
                if (curr.left != null) stack.push(curr.left);
            }
        }
    }

    /**
     * 中序遍历:递归实现
     *
     * @param root
     */
    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    /**
     * 非递归模拟中序遍历
     */
    public void inOrder() {
        if (root != null) {
            Node curr = root;
            Stack<Node> stack = new Stack<>();
            while (true) {
                while (curr != null) { //左子树先全部依次入栈
                    stack.push(curr);
                    curr = curr.left;
                }
                if (stack.isEmpty()) break;
                curr = stack.pop();
                System.out.print(curr.data + " ");
                curr = curr.right; //如果右子树不空的话,右子树的全部左子树全部入栈
            }
        }
    }

    /**
     * 后序遍历,递归实现
     *
     * @param root
     */
    public void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    /**
     * 用栈来模拟后序遍历
     */
    public void postOrder3() {
        if (root != null) {
            Node curr = root;
            Deque<Node> stack = new ArrayDeque<>();
            stack.push(curr);
            Node pre = null;
            while (!stack.isEmpty()) {
                curr = stack.peek();
                if ((curr.left == null && curr.right == null)
                        || (pre == curr.left)
                        || (pre == curr.right)) {
                    System.out.print(curr.data + " ");
                    pre = stack.pop();
                    continue;
                }
                if (curr.right != null) stack.push(curr.right);
                if (curr.left != null) stack.push(curr.left);
            }
        }
    }

    /**
     * 非递归的令一种思路,这个跟递归的执行顺序比较像
     */
    public void postOrder4() {
        if (root != null) if (root != null) {
            Node curr = root;
            Deque<Node> stack = new ArrayDeque<>();
            stack.push(curr);
            Node pre = null;
            curr = curr.left;
            while (!stack.isEmpty()) {
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.peek();
                if ((curr.left == null && curr.right == null)
                        || (curr.right == null && pre == curr.left)
                        || (pre == curr.right)) {
                    System.out.print(curr.data + " ");
                    pre = stack.pop();
                    curr = null;
                } else curr = curr.right;
            }
        }
    }

    /**
     * 层级遍历
     */
    public void levelOrder() {
        if (this.root != null) {
            Node curr = this.root;
            ArrayDeque<Node> queue = new ArrayDeque<>();
            queue.add(curr);
            while (!queue.isEmpty()) {
                curr = queue.poll();
                System.out.print((curr.data + " "));
                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null)
                    queue.add(curr.right);
            }
        }
    }

    /**
     * 删除结点
     *
     * @param data
     */
    public void delete(int data) {
        if (root == null) {
            System.out.println("当前树为空树");
            return;
        }
        Node curr = root; //当前结点
        Node parent = root; //当前结点的父结点
        boolean isLeft = true;
        while (curr != null) {
            if (curr.data == data) break;
            parent = curr;
            if (data < curr.data) {
                curr = curr.left;
                isLeft = true;
            } else { //否则向右寻找
                curr = curr.right;
                isLeft = false;
            }
        }
        if (curr == null) { //当前结点为null,说明没有找到
            System.out.println("没有找到");
            return;
        }
        /**
         * 有三种可能的情况:
         * 1.当前结点没有子结点
         * 2.当前结点有两个子结点
         * 3.当前结点只有一个子结点 -->3.1当前子结点为其左子结点
         *                      -->3.2当前子结点为其右子结点
         */
        //1.当前结点没有子结点
        if (curr.left == null && curr.right == null) {
            if (curr == root) {
                root = null;
                return;
            }
            if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return;
        }
        //2.当前结点有两个子结点,删除操作需要寻找后继结点,将当前结点data与后继结点的data互换,然后将后继结点删除即可
        if (curr.left != null && curr.right != null) {
            Node success = getSuccess(curr);
            curr.data = success.data;
            return;
        }
        //3.当前结点只有一个子结点
        if (curr.left != null) {
            if (curr == root) {
                root = curr.left;
                return;
            }
            if (isLeft) parent.left = curr.left;
            else parent.right = curr.left;
            return;
        }
        if (curr.right != null) { //右子结点不为空
            if (curr == root) {
                root = curr.right;
                return;
            }
            if (isLeft)
                parent.left = curr.right;
            else
                parent.right = curr.right;
        }
    }

    /**
     * 删除后继结点
     * 也就是curr节点的右子树中的最小值
     *
     * @param curr
     * @return
     */
    private Node getSuccess(Node curr) {
        Node success = curr.right;
        Node parent = curr;
        Node child = curr.right;
        while (child != null) {
            parent = success;
            success = child;
            child = child.left;
        }
        if (success == curr.right) {
            if (success.right != null) curr.right = success.right;
            else curr.right = null;
        } else {
            if (success.right != null) parent.left = success.right;
            else parent.left = null;
        }
        success.right = null;
        return success;
    }

    /**
     * 添加数据比当前结点小的做为当前结点的左结点,否则作为右结点
     *
     * @param data
     */
    public void add(int data) {
        Node node = new Node(data);
        if (root != null) {
            Node curr = root;
            Node parent = curr;

            while (curr != null) {
                parent = curr;
                if (data < curr.data) curr = curr.left;
                else curr = curr.right;
            }
            if (data < parent.data) parent.left = node;
            else parent.right = node;
        } else root = new Node(data);
    }

    private class Node {
        Node left;
        Node right;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }
}

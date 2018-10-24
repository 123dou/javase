package testDemo;

import java.util.Scanner;
import java.util.HashMap;

public class Test {
    public static class Node {
        Node left = null;
        Node right = null;
        int value;

        public Node(int val) {
            this.value = val;
        }
    }

    public static int helper(Node root) {
        if (root == null) {
            return 0;
        }
        int left = 0, right = 0;
        if (root.left != null) {
            left = helper(root.left);
        }
        if (root.right != null) {
            right = helper(root.right);
        }
        return ((left >= right) ? left : right) + 1;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        int rootval = s.nextInt();
        int rootfirst = s.nextInt();

        Node root = new Node(rootval);
        Node left = new Node(rootfirst);
        root.left = left;

        HashMap<Integer, Node> nodeMap = new HashMap<Integer, Node>();
        nodeMap.put(rootval, root);
        nodeMap.put(rootfirst, left);

        for (int i = 0; i < num - 2; i++) {
            int parentval = s.nextInt();
            int childval = s.nextInt();
            Node parent = nodeMap.get(parentval);
            Node child = new Node(childval);
            if (parent != null) {
                if (parent.left == null) {
                    parent.left = child;
                } else if (parent.right == null) {
                    parent.right = child;
                }
            } else {
                parent = new Node(parentval);
                parent.left = child;
            }
            nodeMap.put(parentval, parent);
            nodeMap.put(childval, child);
        }
        System.out.println(helper(root));

    }
}

package algs4.tree.rbTree;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class BinaryTreeTest {

    private BinaryTree binaryTree = new BinaryTree();

    @Before
    public void before() {
        add();
    }

    @Test
    public void preOrder() {
        System.out.println("\n前序遍历:递归");
        binaryTree.preOrder(binaryTree.getRoot());
        preOrder1();
    }

    @Test
    public void preOrder1() {
        System.out.println("\n前序遍历:非递归:");
        binaryTree.preOrder();
    }

    /**
     * 测试非递归的中序遍历
     */
    @Test
    public void inOrder() {
        System.out.println("\n中序遍历:非递归:");
        binaryTree.inOrder();
        inOrder2();
    }

    /**
     * 测试递归的中序遍历
     */
    @Test
    public void inOrder2() {
        System.out.println("\n中序遍历:递归:");
        binaryTree.inOrder(binaryTree.getRoot());
    }

    /**
     * 测试递归的后序遍历
     */
    @Test
    public void postOrder2() {
        System.out.println("\n后序遍历:递归:");
        binaryTree.postOrder(binaryTree.getRoot());
    }

    /**
     * 测试非递归的后序遍历
     */
    @Test
    public void postOrder() {
        System.out.println("\n后序遍历:非递归:");
        binaryTree.postOrder3();
        binaryTree.postOrder4();
        postOrder2();
    }

    @Test
    public void levelOrder() {
        System.out.println("\n层级遍历: ");
        binaryTree.levelOrder();
    }

    @Test
    public void delete() {
        System.out.println("删除元素:100");
        binaryTree.delete(100);
        binaryTree.inOrder();
    }

    @Test
    public void add() {
        Random random = new Random(98);
//        for (int i = 0; i < 10; i++) {
//            int n = random.nextInt(200);
//            System.out.print("添加元素: " + n + " ");
//            binaryTree.add(n);
//        }
        binaryTree.add(80);
        binaryTree.add(70);
        binaryTree.add(100);
        binaryTree.add(40);
        binaryTree.add(75);
        binaryTree.add(90);
        binaryTree.add(110);
        binaryTree.add(30);
        binaryTree.add(45);
        binaryTree.add(72);
        binaryTree.add(77);
        binaryTree.add(85);
        binaryTree.add(95);
        binaryTree.add(105);
        binaryTree.add(120);
//        binaryTree.add('E');
//        binaryTree.add('B');
//        binaryTree.add('F');
//        binaryTree.add('A');
//        binaryTree.add('D');
//        binaryTree.add('H');
//        binaryTree.add('C');
//        binaryTree.add('G');
//        binaryTree.add('I');
//        binaryTree.add('K');
//        binaryTree.add('J');
    }
}
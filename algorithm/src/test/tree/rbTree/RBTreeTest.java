package test.tree.rbTree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import algs4.tree.rbTree.*;

/**
 * RBTree Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>04 7, 2018</pre>
 */
public class RBTreeTest {
    private RBTree rbTree;

    @Before
    public void before() throws Exception {
        rbTree = new RBTree();
        rbTree.add(12);
        rbTree.add(1);
        rbTree.add(9);
        rbTree.add(2);
        rbTree.add(0);
        rbTree.add(11);
        rbTree.add(7);
        rbTree.add(19);
        rbTree.add(4);
        rbTree.add(15);
        rbTree.add(18);
        rbTree.add(5);
        rbTree.add(14);
        rbTree.add(13);
        rbTree.add(10);
        rbTree.add(16);
        rbTree.add(6);
        rbTree.add(3);
        rbTree.add(8);
        rbTree.add(17);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: levelOrder()
     */
    @Test
    public void testLevelOrder() {
        rbTree.levelOrder();
        System.out.println(rbTree.getSize());
    }

    /**
     * 测试中序遍历
     */
    @Test
    public void testinOrder() {
    }

    @Test
    public void testRemove() {
        rbTree.delete(12);
        rbTree.delete(1);
        rbTree.delete(9);
        rbTree.delete(2);
        rbTree.delete(0);
        rbTree.delete(11);
        rbTree.delete(7);
        rbTree.delete(19);
        rbTree.delete(4);
        rbTree.delete(15);
        rbTree.delete(18);
        rbTree.delete(5);
        rbTree.delete(14);
        rbTree.delete(13);
        rbTree.delete(10);
        rbTree.delete(16);
        rbTree.delete(6);
        rbTree.delete(3);
        rbTree.delete(8);
        rbTree.delete(17);
        rbTree.levelOrder();
        System.out.println(rbTree.getSize());
    }
}

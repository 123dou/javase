package algs4.tree.rbTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 重些rbt
 */
public class RBTree2 {
    private final boolean BLACK = true;
    private final boolean RED = false;
    private RBNode root;
    private int size;

    /**
     * 中序遍历
     *
     * @return
     */
    public List<Integer> inOrder() {
        List<Integer> nodes = new ArrayList<>();
        if (root == null) return nodes;
        inOrder(root, nodes);
        return nodes;
    }

    private void inOrder(RBNode node, List<Integer> nodes) {
        if (node != null) {
            inOrder(node.left, nodes);
            nodes.add(node.key);
            inOrder(node.right, nodes);
        }
    }


    /**
     * @return
     */
    public List<RBNode> levelOrder() {
        List<RBNode> nodes = new ArrayList<>();
        if (root == null) return nodes;
        Deque<RBNode> q = new ArrayDeque<>();
        RBNode node = root;
        q.add(node);
        while (!q.isEmpty()) {
            node = q.poll();
            nodes.add(node);
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        return nodes;
    }


    /**
     * 删除一个key
     *
     * @param key
     */
    public void delete(int key) {
        if (root == null) return;
        RBNode node = root;
        while (node != null) {
            if (node.key == key) break;
            if (key < node.key) node = node.left;
            else node = node.right;
        }

        if (node == null) return;
        if (node.left != null && node.right != null) {
            RBNode successor = getSuccessor(node);
            node.key = successor.key;
            node = successor;
        }

        RBNode replacement = (node.left == null ? node.right : node.left);
        //有一个结点不为null
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null)  //也就是node == root
                root = replacement;
            else if (node == node.parent.left)
                node.parent.left = replacement;
            else
                node.parent.right = replacement;

            //解除node的所有连接
            node.left = node.right = node.parent = null;
            if (node.color == BLACK) fixAfterDelete(replacement);
        } else { //node没有孩子结点
            if (node == root) root = null;
            else {
                if (node.color == BLACK) fixAfterDelete(node);
                //修复颜色之后再解除连接
                if (node == node.parent.left) node.parent.left = null;
                else node.parent.right = null;
                node.parent = null;
            }
        }

    }

    private void fixAfterDelete(RBNode node) {
        if (colorOf(node) == RED) {
            setColor(node, BLACK);
            return;
        }
        RBNode p = parentOf(node);
        while (node != root && colorOf(p) == BLACK) {
            if (node == leftOf(p)) {
                RBNode bro = rightOf(p);
                if (colorOf(bro) == BLACK) {
                    if (colorOf(leftOf(bro)) == BLACK && colorOf(rightOf(bro)) == BLACK) {
                        setColor(bro, RED);
                        node = p;
                        p = parentOf(node);
                        continue;
                    }

                    if (colorOf(rightOf(bro)) != RED) {
                        bro = leftOf(bro);
                        rightRotate(parentOf(bro));
                    }
                    setColor(bro, BLACK);
                    setColor(rightOf(bro), BLACK);
                    leftRotate(p);
                    return;
                } else {
                    setColor(bro, BLACK);
                    setColor(p, RED);
                    leftRotate(p);
                    break;
                }

            } else {
                RBNode bro = leftOf(p);
                if (colorOf(bro) == BLACK) {
                    if (colorOf(leftOf(bro)) == BLACK && colorOf(rightOf(bro)) == BLACK) {
                        setColor(bro, RED);
                        node = p;
                        p = parentOf(node);
                        continue;
                    }

                    if (colorOf(leftOf(bro)) != RED) {
                        bro = rightOf(bro);
                        leftRotate(parentOf(bro));
                    }

                    setColor(bro, BLACK);
                    setColor(leftOf(bro), BLACK);
                    rightRotate(p);
                    return;
                } else {
                    setColor(bro, BLACK);
                    setColor(p, RED);
                    rightRotate(p);
                    break;
                }
            }
        }

        if (node != root && colorOf(p) == RED) {
            if (node == leftOf(p)) {
                RBNode bro = rightOf(p);
                if (bro == null) {
                    setColor(p, BLACK);
                } else {
                    if (colorOf(leftOf(bro)) == BLACK && colorOf(rightOf(bro)) == BLACK) {
                        setColor(p, BLACK);
                        setColor(bro, RED);
                    } else {
                        if (colorOf(rightOf(bro)) != RED) {
                            bro = leftOf(bro);
                            rightRotate(parentOf(bro));
                        }

                        setColor(p, BLACK);
                        setColor(bro, RED);
                        setColor(rightOf(bro), BLACK);
                        leftRotate(p);
                    }
                }
            } else {
                RBNode bro = leftOf(p);
                if (bro == null) {
                    setColor(p, BLACK);
                } else {
                    if (colorOf(leftOf(bro)) == BLACK && colorOf(rightOf(bro)) == BLACK) {
                        setColor(p, BLACK);
                        setColor(bro, RED);
                    } else {
                        if (colorOf(leftOf(bro)) != RED) {
                            bro = rightOf(bro);
                            leftRotate(parentOf(bro));
                        }

                        setColor(p, BLACK);
                        setColor(bro, RED);
                        setColor(leftOf(bro), BLACK);
                        rightRotate(p);
                    }
                }
            }
        }

        if (node == root) setColor(node, BLACK);
    }

    /**
     * 仅仅是返回后继结点,不作任何的指针改变
     * @param node
     * @return
     */
    private RBNode getSuccessor(RBNode node) {
        RBNode successor = null;
        RBNode temp = node.right;
        while (temp != null) {
            successor = temp;
            temp = temp.left;
        }
        return successor;
    }

    /**
     * 普通二叉查找树的添加
     *
     * @param key
     */
    public void add(int key) {
        if (root == null) {
            root = new RBNode(key);
            root.color = BLACK;
            return;
        }
        RBNode node = root;
        RBNode p = node;
        while (node != null) {
            p = node;
            if (node.key == key) return;
            if (key < node.key) node = node.left;
            else node = node.right;
        }
        RBNode newNode = new RBNode(key);
        if (key < p.key) p.left = newNode;
        else p.right = newNode;
        newNode.parent = p;
        fixAfterAdd(newNode);
        size++;
    }

    /**
     * 添加了之后修正颜色
     *
     * @param node
     */
    private void fixAfterAdd(RBNode node) {
        setColor(node, RED);
        while (node != root) {
            if (colorOf(parentOf(node)) == BLACK) return;
            if (parentOf(node) == parentOf(parentOf(node)).left) {
                //父结点的兄弟结点为红
                if (colorOf(rightOf(parentOf(parentOf(node)))) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(rightOf(parentOf(parentOf(node))), BLACK);
                    node = parentOf(parentOf(node));
                    setColor(node, RED);
                    continue;
                }

                if (node == parentOf(node).right) {
                    RBNode t = parentOf(node);
                    leftRotate(parentOf(node));
                    node = t;
                }
                setColor(parentOf(node), BLACK);
                setColor(parentOf(parentOf(node)), RED);
                rightRotate(parentOf(parentOf(node)));
                return;
            } else {
                //父结点的兄弟结点为红
                if (colorOf(leftOf(parentOf(parentOf(node)))) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(leftOf(parentOf(parentOf(node))), BLACK);
                    node = parentOf(parentOf(node));
                    setColor(node, RED);
                    continue;
                }

                if (node == parentOf(node).left) {
                    RBNode t = parentOf(node);
                    rightRotate(parentOf(node));
                    node = t;
                }
                setColor(parentOf(node), BLACK);
                setColor(parentOf(parentOf(node)), RED);
                leftRotate(parentOf(parentOf(node)));
                return;
            }
        }
        setColor(root, BLACK);
    }

    private void setColor(RBNode x, boolean color) {
        x.color = color;
    }

    private RBNode leftOf(RBNode x) {
        return x.left;
    }

    private RBNode rightOf(RBNode x) {
        return x.right;
    }

    private boolean colorOf(RBNode x) {
        return x == null ? BLACK : x.color;
    }

    private RBNode parentOf(RBNode x) {
        return x == null ? null : x.parent;
    }


    /**
     * 左旋
     *
     * @param x
     */
    private void leftRotate(RBNode x) {
        if (x == null) return;
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;
        if (parentOf(x) != null) {
            if (x == parentOf(x).left) parentOf(x).left = y;
            else parentOf(x).right = y;
        } else root = y;
        y.parent = parentOf(x);
        y.left = x;
        x.parent = y;
    }

    /**
     * 右旋
     *
     * @param x
     */
    private void rightRotate(RBNode x) {
        if (x == null) return;
        RBNode y = x.left;
        x.left = y.right;
        if (y.right != null) y.right.parent = x;
        if (parentOf(x) != null) {
            if (x == parentOf(x).left) parentOf(x).left = y;
            else parentOf(x).right = y;
        } else root = y;
        y.parent = parentOf(x);
        y.right = x;
        x.parent = y;
    }

    class RBNode {
        int key;
        RBNode left, right, parent;
        boolean color;

        RBNode(int key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return key + ":" + color;
        }
    }
}

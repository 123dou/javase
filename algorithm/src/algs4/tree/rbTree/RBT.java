package algs4.tree.rbTree;

import java.util.*;

public class RBT {
    private final boolean BLACK = true;
    private final boolean RED = false;
    private RBNode root;
    private int size;


    public List<RBNode> levelOrder() {
        List<RBNode> nodes = new ArrayList<>();
        if (root == null) return nodes;
        Deque<RBNode> q = new ArrayDeque<>();
        RBNode t = root;
        q.add(t);
        while (!q.isEmpty()) {
            t = q.poll();
            nodes.add(t);
            if (t.left != null) q.add(t.left);
            if (t.right != null) q.add(t.right);
        }
        return nodes;
    }


    class RBNode {
        int key;
        RBNode left;
        RBNode right;
        RBNode parent;
        boolean color = BLACK;

        /**
         * Make a new cell with given key, value, and parent, and with
         * {@code null} child links, and BLACK color.
         */
        RBNode(int key, RBNode parent) {
            this.key = key;
            this.parent = parent;
        }

        RBNode(int k) {
            this(k, null);
        }

        /**
         * Returns the key.
         *
         * @return the key
         */
        public int getKey() {
            return key;
        }

        public String toString() {
            return key + ":" + color;
        }
    }


    public void add(int key) {
        if (root == null) {
            root = new RBNode(key);
            root.color = BLACK;
            return;
        }
        RBNode t = root;
        RBNode p = root;
        while (t != null) {
            p = t;
            if (key == t.key) return;
            if (key < t.key) t = t.left;
            else t = t.right;
        }
        RBNode node = new RBNode(key);
        if (key < p.key) {
            p.left = node;
        } else p.right = node;
        node.parent = p;
        fixAfterInsertion(node);
    }

    public void delete(int key) {
        if (root == null) return;
        RBNode t = root;
        RBNode d = null;
        while (t != null) {
            if (t.key == key) {
                d = t;
                break;
            }
            if (key < t.key) t = t.left;
            else t = t.right;
        }
        if (d == null) return;
        deleteRBNode(d);
    }

    /**
     * Returns the successor of the specified RBNode, or null if no such.
     */
    RBNode successor(RBNode t) {
        if (t == null)
            return null;
        else if (t.right != null) {
            RBNode p = t.right;
            while (p.left != null)
                p = p.left;
            return p;
        } else {
            RBNode p = t.parent;
            RBNode ch = t;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    /**
     * Returns the predecessor of the specified RBNode, or null if no such.
     */
    RBNode predecessor(RBNode t) {
        if (t == null)
            return null;
        else if (t.left != null) {
            RBNode p = t.left;
            while (p.right != null)
                p = p.right;
            return p;
        } else {
            RBNode p = t.parent;
            RBNode ch = t;
            while (p != null && ch == p.left) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    /**
     * Balancing operations.
     * <p>
     * Implementations of rebalancings during insertion and deletion are
     * slightly different than the CLR version.  Rather than using dummy
     * nilnodes, we use a set of accessors that deal properly with null.  They
     * are used to avoid messiness surrounding nullness checks in the main
     * algorithms.
     */

    private boolean colorOf(RBNode p) {
        return (p == null ? BLACK : p.color);
    }

    private RBNode parentOf(RBNode p) {
        return (p == null ? null : p.parent);
    }

    private void setColor(RBNode p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private RBNode leftOf(RBNode p) {
        return (p == null) ? null : p.left;
    }

    private RBNode rightOf(RBNode p) {
        return (p == null) ? null : p.right;
    }

    /**
     * From CLR
     */
    private void rotateLeft(RBNode p) {
        if (p != null) {
            RBNode r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;
        }
    }

    /**
     * From CLR
     */
    private void rotateRight(RBNode p) {
        if (p != null) {
            RBNode l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }

    /**
     * From CLR
     */
    private void fixAfterInsertion(RBNode x) {
        x.color = RED;

        while (x != null && x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                RBNode y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                RBNode y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }

    /**
     * Delete node p, and then rebalance the tree.
     */


    private void deleteRBNode(RBNode p) {
        size--;

        // If strictly internal, copy successor's element to p and then make p
        // point to successor.
        if (p.left != null && p.right != null) {
            RBNode s = successor(p);
            p.key = s.key;
            p = s;
        } // p has 2 children

        // Start fixup at replacement node, if it exists.
        RBNode replacement = (p.left != null ? p.left : p.right);

        if (replacement != null) {
            // Link replacement to parent
            replacement.parent = p.parent;
            if (p.parent == null)
                root = replacement;
            else if (p == p.parent.left)
                p.parent.left = replacement;
            else
                p.parent.right = replacement;

            // Null out links so they are OK to use by fixAfterDeletion.
            p.left = p.right = p.parent = null;

            // Fix replacement
            if (p.color == BLACK)
                fixAfterDeletion(replacement);
        } else if (p.parent == null) { // return if we are the only node.
            root = null;
        } else { //  No children. Use self as phantom replacement and unlink.
            if (p.color == BLACK)
                fixAfterDeletion(p);

            if (p.parent != null) {
                if (p == p.parent.left)
                    p.parent.left = null;
                else if (p == p.parent.right)
                    p.parent.right = null;
                p.parent = null;
            }
        }
    }

    /**
     * From CLR
     */
    private void fixAfterDeletion(RBNode x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) {
                RBNode sib = rightOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }

                if (colorOf(leftOf(sib)) == BLACK &&
                        colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else { // symmetric
                RBNode sib = leftOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colorOf(rightOf(sib)) == BLACK &&
                        colorOf(leftOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }

        setColor(x, BLACK);
    }

}

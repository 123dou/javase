package algs4.tree.rbTree;

import java.util.*;

/**
 * 红黑树满足以下五个条件的二叉树:
 * 1.结点是红色或者黑色
 * 2.根结点是黑色的
 * 3.每个null结点是黑色的
 * 4.每个红色结点的两个子结点一定是黑色的
 * 5.从任一结点到其每个结点的所有路径都包含相同数量的黑色结点
 */
public class RBTree {
    private RBNode root; //根结点
    int size; //用来计算结点的总数量

    public RBNode getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public List<RBNode> inOrder() {
        List<RBNode> nodes = new ArrayList<>();
        if (root == null) return nodes;
        inOrder(root, nodes);
        return nodes;
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    private void inOrder(RBNode root, List<RBNode> nodes) {
        if (root != null) {
            inOrder(root.left, nodes);
            nodes.add(root);
            inOrder(root.right, nodes);
        }
    }

    /**
     * '
     * 层级遍历
     */
    public void levelOrder2() {
        if (this.root == null) {
            System.out.println("EMPTY TREE");
            return;
        }
        //队列结构,先进先出
        Queue<RBNode> queue = new ArrayDeque<>();
        RBNode temp = null;
        queue.add(this.root);
        while (!queue.isEmpty()) {
            temp = queue.poll();  //弹出头元素并删除
            System.out.print(temp.key + "--" + temp.color + " ");
            if (temp.left != null)
                queue.add(temp.left);
            if (temp.right != null)
                queue.add(temp.right);
        }
        System.out.println();
    }


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

    private boolean isLeft; //删除的时候做为被删除元素是否是其父结点的判断

    /**
     * 删除一个key:
     * 1.root结点为null,直接返回
     * 2.寻找包含key的结点
     * 3.根结点root不为null:
     * 3.0 被删除结点curr的两个子结点都不为null
     * 3.1 被删除结点curr的两个子结点都为null
     * 3.2 被删除结点curr只有一个结点为null: 3.2.0 左结点为null,右结点不为null ；3.2.1 左结点为null,右结点不为null
     * 4. 删除结点之后进行颜色的纠正
     *
     * @param key
     */
    public void delete(int key) {
        //1.0 root结点为null
        if (this.root == null) return;
        //2. 寻找包含key的结点
        RBNode curr = null; //当前结点,也就是要被删除的结点
        curr = this.root;
        while (curr != null) {
            if (key == curr.key) { //如果当前结点的key与要删除的key,则找到了要删除的结点
                break;
            } else if (key < curr.key) { //如果key小于当前结点的key则,再与当前结点的左结点继续比较
                curr = curr.left;
                isLeft = true; //被删除结点为其父结点的左子结点
            } else { //否则如果key大于当前结点的key,再与当前结点的右结点继续比较
                curr = curr.right;
                isLeft = false; //被删除结点为其父结点的右子结点
            }
        }
        if (curr == null) return;
        /**
         * 下面分情况讨论:
         * 3.0 两个结点都不为null
         * 3.1 两个结点都为null
         * 3.2 只有一个结点为null： 3.2.0 左结点不为null,右结点为null 3.2.1 左结点为null, 右结点不为null
         */
        if (curr.left != null && curr.right != null) { //3.0 两个结点都不为null
            RBNode success = getSuccess(curr);
            curr.key = success.key; //将当前结点的key与后继结点的key互换
            removeFixUp(success); //颜色调整
            this.size--;
            return;
        } else if (curr.left == null && curr.right == null) { //3.1 两个结点都为null
            if (curr == this.root) { //当前结点为根结点
                this.root = null;
                return; //这种情况不需要调整颜色
            } else { //当结点不为根结点
                if (isLeft) { //当前结点为其父结点的左结点
                    curr.parent.left = null;
                } else { //当前结点为其父结点的右结点
                    curr.parent.right = null;
                }
            }
        } else { //3.2 只有一个结点不为null
            if (curr.left != null) { //当前左结点不为null
                if (curr == this.root) { //如果当前结点为根结点,则要重新设置根结点
                    this.root = curr.left;
                    curr.left.parent = null;
                } else { //当前结的不为根结点
                    if (isLeft) { //如果当前结点为其父结点的左结点
                        curr.parent.left = curr.left;
                    } else { //当前结点为其父结点的右子结点
                        curr.parent.right = curr.left;
                    }
                    //设置其左子结点的父结点
                    curr.left.parent = curr.parent;
                }
            } else { //当前结点的右子结点不为null
                if (curr == this.root) { //当前结点为根结点
                    this.root = curr.right;
                    curr.right.parent = null;
                } else {
                    if (isLeft) { //当前结点为其父结点的左子结点
                        curr.parent.left = curr.right;
                    } else { //当前结点为其父结点的右子结点
                        curr.parent.right = curr.right;
                    }
                    //设置其右子结点的父结点
                    curr.right.parent = curr.parent;
                }
            }
        }
        removeFixUp(curr);
        this.size--;
    }

    /**
     * 删除结点之后的颜色调整:
     * 1. 删除结点的颜色为红色,直接删除
     * 2. 删除结点为黑色 :
     * --2.0 删除结点只有一个子结点,且为红色
     * ----2.1 -> 删除结点的父结点为黑色 :
     * --------2.1.1 -> 删除结点是其父结点的左子结点 :
     * <p>
     * ------------2.1.1.1 -> 删除结点的兄第结点为黑色 :
     * ----------------2.1.1.1.1 -> 删除结点的兄第结点的两个子结点都为黑色: 将该情况转为2.2 即 ->将兄第结点设为红色,将删除结点当被删除结点继续向上调整
     * ----------------2.1.1.1.2 -> 删除结点的兄第结点的左子结点为红色,右子结点为黑色:
     * 将该情况转换为2.1.1.1.3 即->交换兄第结点与兄第结点的左子结点的颜色,再以兄第结点为支点右转,再继续
     * ---------------2.1.1.1.3 -> 删除结点的兄第结点的右子结点为红色,左子结点随便 : 将右子结点设为黑色,将以删除结点的父结点为支点左旋,完成
     * <p>
     * -----------2.1.1.2 -> 删除结点的兄第结点为红色: 将该请况转为2.2 即 -> 互换兄第结点与父结点的颜色,以父结点为支点左转,再继续
     * <p>
     * --------2.1.2 -> 删除结点是其父结点的右子结点: 分析情况根2.1.1 互为镜像
     * <p>
     * --2.2 -> 删除结点的父结点为红色:
     * ------2.2.1 -> 删除结点是其父结点的左子结点:
     * ----------2.2.1.1 -> 删除结点的兄第结点为黑色,且两个子结点都为黑色: 互换兄第结点与父结点的颜色,完成
     * ----------2.2.1.2 -> 删除结点的兄第结点为黑色,且左子结点为红色,右子结点为黑色: 将该情况转为2.2.1.3 即 -> 互换兄第结点与兄第结点左子结点的颜色,再以兄第结点为支点右转,再继续
     * ----------2.2.1.3 -> 删除结点的兄第结点为黑色,且右子结点为红色,左子结点随便: 将父结点和兄第结点的右结点设为黑色,兄第结点设为红色,再左转,完成
     * <p>
     * ------2.2.2 -> 删除结点是其父结点的右子结点: 分析与2.2.1互为镜像
     *
     * @param curr
     */
    private void removeFixUp(RBNode curr) {
        //1. 删除结点为红色,直接返回
        if (curr.color == Color.RED) return;

        //2.0 删除结点为黑色,且有一个红色的子结点,直接将子结点设为黑
        if (curr.left != null && isRed(curr.left)) {
            curr.left.color = Color.BLACK;
            return;
        }
        if (curr.right != null && isRed(curr.right)) {
            curr.right.color = Color.BLACK;
            return;
        }
        if (curr.parent != null) {
            //2.1 如果删除结点的父结点为黑
            while (isBlack(curr.parent)) {
                if (isLeft) { //如果被删除结点为父结点的左结点
                    RBNode bro = curr.parent.right; // 当前结点的兄第结点
                    //2.1.1 当前兄第结点及其子结点为黑
                    if (bro == null || (isBlack(bro.left) && isBlack(bro.right) && isBlack(bro))) {
                        setRed(bro);
                        curr = curr.parent;
                        if (curr.parent == null) { //当前结点为根结点,直接退出循环
                            break;
                        } else { //否则当前结点不是根结点,重新判断当前结点是其父结点的左还是右结点
                            isLeft = isLeft(curr);
                        }
                        continue;
                    }
                    //兄第结点为黑,两个子结点不全为黑
                    if (isBlack(bro)) {
                        //2.1.2 当前兄第结点的左子结点为红,右子结点为黑
                        if (isRed(bro.left) && isBlack(bro.right)) {
                            setBlack(bro);
                            setRed(bro.left);
                            rightRotate(bro);
                        }
                        //2.1.3 当前结点的右子结点为红,左结点随便
                        if (isRed(bro.right)) {
                            setBlack(bro.right);
                            leftRotate(curr.parent);
                            return;
                        }
                    } else { //兄第结点为红
                        setRed(curr.parent);
                        setBlack(bro);
                        leftRotate(curr.parent);
                    }
                } else { //否则被删除结点为父结点的右结点
                    RBNode bro = curr.parent.left;
                    if (bro == null || (isBlack(bro.left) && isBlack(bro.right) && isBlack(bro))) {
                        setRed(bro);
                        curr = curr.parent;
                        if (curr.parent == null) { //当前结点为根结点,直接退出循环
                            break;
                        } else { //否则当前结点不是根结点,重新判断当前结点是其父结点的左还是右结点
                            isLeft = isLeft(curr);
                        }
                        continue;
                    }
                    //兄第结点为黑,两个子结点不全为黑
                    if (isBlack(bro)) {
                        //2.1.2 当前兄第结点的左子结点为红,右子结点为黑
                        if (isRed(bro.right) && isBlack(bro.left)) {
                            setBlack(bro);
                            setRed(bro.right);
                            rightRotate(bro);
                        }
                        //2.1.3 当前结点的右子结点为红,左结点随便
                        if (isRed(bro.left)) {
                            setBlack(bro.left);
                            leftRotate(curr.parent);
                            return;
                        }
                    } else { //兄第结点为红
                        setRed(curr.parent);
                        setBlack(bro);
                        rightRotate(curr.parent);
                    }
                }
            }

            //2.2 如果删除结点的父结点为红色
            if (curr.parent != null) {
                if (isLeft) { //删除结点为其父结点的左子结点
                    RBNode bro = curr.parent.right;
                    //2.2.0 兄第结点及其两个子结点都为黑
                    if (bro == null || (isBlack(bro.left) && isBlack(bro.right) && isBlack(bro))) {
                        setBlack(curr.parent);
                        setRed(bro);
                        return;
                    }
                    //2.2.1 兄第结点的左子结点为红,右子结点为黑
                    if (isRed(bro.left) && isBlack(bro.right)) {
                        setRed(bro);
                        setBlack(bro.left);
                        rightRotate(bro);
                    }
                    bro = curr.parent.right; //旋转之后重新给bro赋值
                    //2.2.2 兄第结点的右子结点为红,左子结点随便
                    if (isRed(bro.right)) {
                        setRed(bro);
                        setBlack(curr.parent);
                        setBlack(bro.right);
                        leftRotate(curr.parent);
                        return;
                    }

                } else { //删除结点为其父结点的右子结点
                    RBNode bro = curr.parent.left;
                    //2.2.0 兄第结点及其两个子结点都为黑
                    if (bro == null || (isBlack(bro.left) && isBlack(bro.right) && isBlack(bro))) {
                        setBlack(curr.parent);
                        setRed(bro);
                        return;
                    }
                    //2.2.1 兄第结点的右子结点为红,左子结点为黑
                    if (isRed(bro.right) && isBlack(bro.left)) {
                        setRed(bro);
                        setBlack(bro.right);
                        leftRotate(bro);
                    }
                    bro = curr.parent.left; //旋转之后重新给bro赋值
                    //2.2.2 兄第结点的左子结点为红,右子结点随便
                    if (isRed(bro.left)) {
                        setRed(bro);
                        setBlack(curr.parent);
                        setBlack(bro.left);
                        rightRotate(curr.parent);
                        return;
                    }
                }
            }
        }
        //3. 当前结点为根结点
        setBlack(this.root);
    }

    /**
     * 如果当前结点是其父结点的左子结点返回true否则返回false
     *
     * @param curr
     * @return
     */
    private boolean isLeft(RBNode curr) {
        if (curr.parent == null) {
            return false;
        }
        if (curr == curr.parent.left)
            return true;
        else
            return false;
    }

    /**
     * 如果当前结点为null或当前结点的颜色为黑返回true
     *
     * @param node
     * @return
     */
    private boolean isBlack(RBNode node) {
        if (node == null || node.color == Color.BLACK)
            return true;
        else
            return false;
    }

    /**
     * 获得当前结点的后继结点
     *
     * @param curr
     * @return
     */
    private RBNode getSuccess(RBNode curr) {
        RBNode temp = curr.right;
        RBNode success = null; //后继结点
        while (temp != null) {
            success = temp;
            temp = temp.left;
        }
        if (curr.right == success) { //如果后继结点就是被删除结点的右结点
            if (success.right != null) { //如果后继结点有右子结点
                curr.right = success.right;
                success.right.parent = curr; //设置后继结点的右子结点为当前结点
            } else {
                //如果后继结点没有右子结点,则直接将当前的右子结点设为null,即删除后继结点
                curr.right = null;
            }
            isLeft = false;
        } else { //后继结点不是被删除结点的右子结点
            if (success.right != null) { //如果后继结点有右子结点
                success.parent.left = success.right;
                success.right.parent = success.parent;
            } else {
                //如果没有右子结点直接将后继结点删除
                success.parent.left = null;
            }
            isLeft = true;
        }
        return success; //返回后继结点
    }


    /**
     * 添将操作
     *
     * @param key
     */
    public void add(int key) {
        //生成一个node结点,且其颜色为红色
        RBNode node = new RBNode(key, Color.RED);
        //调用其私有的重载方法,插入结点
        add(node);
        size++;
    }

    /**
     * 插入结点的红黑树中
     *
     * @param node
     */
    private void add(RBNode node) {
        if (this.root == null) {
            this.root = node;
            setBlack(node);
            return;
        }
        RBNode parent;  //临时结点的父结点
        RBNode temp;   //临时结点用来遍历树
        temp = this.root;
        while (true) {
            parent = temp;
            if (node.key == temp.key) return;
            if (node.key < temp.key) { //如果插入的结点的关键值小与当前结点关键值
                temp = temp.left; //则继续访问左结点
                if (temp == null) { //如果左结点为空,则插入这里了,且设置其父结点
                    parent.left = node;
                    node.parent = parent;
                    break;
                }
            } else { //如果插入结点的关键值大与当前结点的关键值
                temp = temp.right; //则继续访问右结点
                if (temp == null) { //如果右结点为空,则插入这里,并设置其父结点
                    parent.right = node;
                    node.parent = parent;
                    break;
                }
            }
        }
        //插入完成之后,需要进行颜色调整
        insertFixUp(node);
    }

    /**
     * 完成二叉树的插入之后需要进行颜色的调整,以满足红黑树的性质
     *
     * @param node
     */
    private void insertFixUp(RBNode node) {
        RBNode parent = null;
        RBNode gparent = null;
        //当其父结点不为空,和父结点为红时进行调整
        while ((parent = parentOf(node)) != null && isRed(parent)) {
            //当父结点为红色时,是一定有祖父结点的而且其祖父结点一定是黑色
            gparent = parentOf(parent);
            //当其父结点为其祖父结点的左边时
            if (parent == gparent.left) {
                RBNode uncle = gparent.right; //其叔结点就为其祖父结点的右结点
                //case1:如果其叔结点为红色时,则需要循环进行以下操作来一个替代结点的叔叔结点不是红色的
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    //将祖父结点设为当前结点,向前寻找,直到"当前结点"满足2,3情况
                    node = gparent;
                    continue;
                }
                //case2:如果其叔叔结点为黑色,且插入结点为其父结点的右结点,则需要调整至足case3
                if (node == parent.right) {
                    RBNode temp = null;
                    //先左旋,然后将原来的父结点当作插入结点,原来的插入结点当作父结点
                    leftRotate(parent);
                    temp = parent;
                    parent = node;
                    node = temp;
                }
                //case3:其叔叔结点为黑色,且插入结点为其父结点的左结点
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
                break;
            } else { //如果插入结点的父结点为其祖父结点的右结点,则进行镜像操作即可
                RBNode uncle = gparent.left;
                //case1:叔叔结点为红色
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    //将祖父结点设为当前结点,向前寻找,直到"当前结点"满足2,3情况
                    node = gparent;
                    continue;
                }
                //case2:叔叔结点为黑色,且插入结点为其父结点的左结点
                if (node == parent.left) {
                    RBNode temp = null;
                    rightRotate(parent);
                    temp = parent;
                    parent = node;
                    node = temp;
                }
                //case3:叔叔结点为黑色,且插入结点为其父结点的有结点
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
                break;
            }
        }
        //将根结点设为黑色
        if (this.root.color == Color.RED)
            setBlack(this.root);
    }

    /**
     * 将结点设为红色
     *
     * @param node
     */
    private void setRed(RBNode node) {
        if (node != null)
            node.color = Color.RED;
    }

    /**
     * 判断插入的结点是否是红色
     *
     * @param node
     * @return
     */
    private boolean isRed(RBNode node) {
        if (node != null && node.color == Color.RED)
            return true;
        else
            return false;
    }

    private RBNode parentOf(RBNode node) {
        return node.parent;
    }

    /**
     * 将结点颜色变为黑色
     *
     * @param node
     */
    private void setBlack(RBNode node) {
        if (node != null)
            node.color = Color.BLACK;
    }


    /**
     * 右旋操作:以y为支点来右旋
     * p                                p
     * /                                /
     * y       -> 以y为支点右旋:          x
     * /  \                             /  \
     * x    ry                         lx   y
     * /  \                                /  \
     * lx   rx                           rx   ry
     *
     * @param y
     */
    private void rightRotate(RBNode y) {
        //将x设为y的左子结点
        RBNode x = y.left;
        //将y的左子结点设为x的右子结点
        y.left = x.right;
        //如果x的右子结点不为空,则将其父结点设为y
        if (x.right != null) {
            x.right.parent = y;
        }
        //如果y父子结点不为空则将x的父结点设为y的父结点
        if (y.parent != null) {
            x.parent = y.parent;
            //根据y之前为其父结点的左还是右结点还设置y为其父结点的左还是右结点
            if (y == y.parent.left)
                x.parent.left = x;
            else
                x.parent.right = x;
        } else {  //否则y为根结点,则直接将x设为根结点
            this.root = x;
            x.parent = null; //需要将根结点的父结点设为null
        }
        //最后将x的右结点设为y,将y的父结点设为x
        x.right = y;
        y.parent = x;
    }

    /**
     * 左旋:以x为支点旋转,即
     * p                              p
     * /                              /
     * x                              y
     * /  \   ->以x为支点左旋          /  \
     * lx   y                         x   ry
     * /  \                       / \
     * ly   ry                    lx  ly
     *
     * @param x
     */
    private void leftRotate(RBNode x) {
        //将x.right设为y
        RBNode y = x.right;
        //将x的右子结点设为y的左子结点
        x.right = y.left;
        //如果y的左子结点不为空,那么就将y的左子结点的父结点设为x
        if (y.left != null) {
            y.left.parent = x;
        }
        //如果x的父结点不空那么则将y的父结点设为x的父结点
        if (x.parent != null) {
            y.parent = x.parent;
            //根据原来x是其父结点的左结点还是右结点来设置y为其父结点的左结点还是右结点
            if (x == x.parent.left)
                y.parent.left = y;
            else
                y.parent.right = y;
        } else { //如果x的父结点为空那么,x是根结点,所以直接将y设为根结点
            this.root = y;
            y.parent = null; //将根结点的父结点设为null
        }
        //最后将y的左结点设为x,将x的父结点设为y
        y.left = x;
        x.parent = y;
    }

    /**
     * @author dou
     * 结点类
     */
    class RBNode {
        int key;
        Color color;
        RBNode left;
        RBNode right;
        RBNode parent;

        /**
         * 构造方法,生成一个特定key和颜色的结点
         *
         * @param key
         * @param color
         */
        public RBNode(int key, Color color) {
            this.key = key;
            this.color = color;
        }

        public RBNode() {
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return key + ":" + color;
        }
    }

    /**
     * 枚举:用来表示结点颜色
     */
    enum Color {
        RED, BLACK
    }
}

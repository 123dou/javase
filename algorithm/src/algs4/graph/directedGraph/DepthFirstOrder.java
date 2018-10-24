package algs4.graph.directedGraph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 如果将dfs()的参数顶点保存在一个数据结构中, 遍历这个数据结构实际上就能访问图中的所有顶点,遍历的顺序取决
 * 于这个数据结构的性质以及是在递归调用之前还是之后进行保存.
 * 1.前序:在递归调用之前就加入队列
 * 2.后序:在递归调用之后加入队列
 * 3.逆后序:在第归调用之后将顶点压入栈顶
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre; //所有顶点的前序排列
    private Queue<Integer> post; //所有顶点的后序排列
    private Deque<Integer> reversePost; //所有顶点的逆后序排列

    public DepthFirstOrder(DiGraph diGraph) {
        pre = new ArrayDeque<>();
        post = new ArrayDeque<>();
        reversePost = new ArrayDeque<>();
        marked = new boolean[diGraph.getV()];
        for (int s = 0; s < diGraph.getV(); s++) dfs(diGraph, s);
    }


    private void dfs(DiGraph diGraph, int v) {
        marked[v] = true;
        pre.add(v);
        for (int w : diGraph.adj(v)) {
            if (!marked[w])
                dfs(diGraph, w);
        }
        post.add(v);
        reversePost.push(v);
    }


    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}

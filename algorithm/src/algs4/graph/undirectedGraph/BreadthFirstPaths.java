package algs4.graph.undirectedGraph;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 该类的构造方法接收一幅图和一个顶点,可以找到所有该顶点能到达的路径；用的是广度优先搜索
 */
public class BreadthFirstPaths {
    private boolean[] marked; //这个顶点上面调用过了dfs了吗
    private int[] edgeTo; //从起点到一个顶点的已知路径上的最后一个顶点
    private int s; //起点

    public BreadthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.getV()];
        edgeTo = new int[graph.getV()];
        this.s = s;
        dfs(graph, s);
    }

    /**
     * 在graph中找所有以v为起点的路径
     *
     * @param graph
     * @param v
     */
    private void dfs(Graph graph, int v) {
        Queue<Integer> queue = new ArrayDeque<>();
        marked[v] = true;
        queue.add(v);
        while (!queue.isEmpty()) {
            int n = queue.poll();
            for (int w : graph.adj(n))
                if (!marked[w]) {
                    edgeTo[w] = n;
                    marked[n] = true;
                    queue.add(w);
                }
        }
    }

    /**
     * v跟s是否连通
     *
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 返回从s到v的路径
     *
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        LinkedList<Integer> path = new LinkedList<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

}

package algs4.graph.WeightGraph.undirectedGraph;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最小生成树
 */
public class LazyPrimMST {
    private boolean[] marked; //最小生成树的顶点
    private Queue<Edge> mst; //最小生成树的边
    private Queue<Edge> pq; //优先队列

    public LazyPrimMST(EdgeWeightedGraph graph) {
        pq = new PriorityQueue<>();
        mst = new ArrayDeque<>();
        marked = new boolean[graph.getV()];
        visit(graph, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.add(e);
            if (!marked[v]) visit(graph, v);
            if (!marked[w]) visit(graph, w);
        }
    }

    /**
     * 标记顶点v并将所有连接v和未被标记顶点的边加入pq
     *
     * @param graph
     * @param v
     */
    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v))
            if (!marked[e.other(v)]) pq.add(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double sum = 0;
        for (Edge e : edges())
            sum += e.getWeight();
        return sum;
    }
}

package algs4.graph.WeightGraph.undirectedGraph;

import algs4.union_find.Quick_union;
import algs4.union_find.WeightedQuickUnionUF;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph graph) {
        mst = new ArrayDeque<>();
        Queue<Edge> pq = new PriorityQueue<>();
        for (Edge e : graph.edges()) pq.contains(e);
        Quick_union uf = new WeightedQuickUnionUF(graph.getV());
        while (!pq.isEmpty() && mst.size() < graph.getV() - 1) {
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if (uf.connect(v, w)) continue;
            uf.union(v, w);
            mst.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}

package algs4.graph.WeightGraph.undirectedGraph;

import algs4.graph.IndexMinPQ;

/**
 * 最段路径,即时版本
 */
public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> indexMinPQ;
    private boolean[] marked;

    public PrimMST(EdgeWeightedGraph graph) {
        edgeTo = new Edge[graph.getV()];
        distTo = new double[graph.getV()];
        indexMinPQ = new IndexMinPQ<>(graph.getV());
        marked = new boolean[graph.getV()];
        for (int i = 0; i < graph.getV(); i++) distTo[i] = Double.POSITIVE_INFINITY;
        distTo[0] = 0.0;
        indexMinPQ.insert(0, 0.0);
        while (!indexMinPQ.isEmpty()) {
            visit(graph, indexMinPQ.deleteMin());
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            if (e.getWeight() < distTo[w]) {
                distTo[w] = e.getWeight();
                edgeTo[w] = e;
                if (!indexMinPQ.contains(w)) indexMinPQ.change(w, distTo[w]);
                else indexMinPQ.insert(w, distTo[w]);
            }
        }
    }

}

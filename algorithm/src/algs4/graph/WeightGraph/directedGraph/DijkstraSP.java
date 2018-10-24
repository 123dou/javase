package algs4.graph.WeightGraph.directedGraph;

import algs4.graph.IndexMinPQ;

import java.util.LinkedList;

public class DijkstraSP {
    private DirectedEdge[] edgeTo; //v --> w ,w关联的边
    private double[] distTo; //从源边到某个顶点的最小权值的边
    private IndexMinPQ<Double> indexMinPQ; //索引优先队列

    public DijkstraSP(EdgeWeightedDigraph graph, int s) {
        edgeTo = new DirectedEdge[graph.getV()];
        distTo = new double[graph.getV()];
        indexMinPQ = new IndexMinPQ<>(graph.getV());
        for (int i = 0; i < graph.getV(); i++) distTo[i] = Double.POSITIVE_INFINITY;
        distTo[0] = 0.0;
        indexMinPQ.insert(s, 0.0);
        while (!indexMinPQ.isEmpty()) {
            relax(graph, indexMinPQ.deleteMin());
        }
    }

    /**
     * 这个其实就是动态规划
     *
     * @param graph
     * @param v
     */
    private void relax(EdgeWeightedDigraph graph, int v) {
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                edgeTo[w] = e;
                distTo[w] = distTo[v] + e.getWeight();
                if (indexMinPQ.contains(w)) indexMinPQ.change(w, distTo[w]);
                else indexMinPQ.insert(w, distTo[w]);
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        LinkedList<DirectedEdge> iter = new LinkedList<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            iter.push(e);
        return iter;
    }

}

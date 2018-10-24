package algs4.graph.WeightGraph.directedGraph;

import com.sun.deploy.panel.ITreeNode;
import algs4.graph.directedGraph.DiGraph;
import algs4.graph.directedGraph.Topological;

import java.util.LinkedList;

public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph graph, int s) {
        edgeTo = new DirectedEdge[graph.getV()];
        distTo = new double[graph.getV()];
        for (int i = 0; i < graph.getV(); i++) distTo[i] = Double.POSITIVE_INFINITY;
        DiGraph diGraph = new DiGraph(graph.getV());
        for (DirectedEdge e : graph.edges()) {
            int v = e.from();
            int w = e.to();
            diGraph.addEdges(v, w);
        }
        Topological topo = new Topological(diGraph);
        distTo[s] = 0.0;
        for (int v : topo.getOrder()) {
            relax(graph, v);
        }
    }

    private void relax(EdgeWeightedDigraph graph, int v) {
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
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

package algs4.graph.WeightGraph.directedGraph;

import java.util.ArrayList;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private ArrayList<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int v = 0; v < V; v++) adj[v] = new ArrayList<>();
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        ArrayList<DirectedEdge> edges = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v))
                edges.add(e);
        }
        return edges;
    }
}

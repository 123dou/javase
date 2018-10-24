package algs4.graph.WeightGraph.undirectedGraph;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class EdgeWeightedGraph {
    private final int V; //顶点的总数
    private int E; //边的总数
    private ArrayList<Edge>[] adj; //邻接表

    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        LinkedHashSet<Edge> edges = new LinkedHashSet<>();
        for (int i = 0; i < V; i++) {
            for (Edge w : adj(i))
                edges.add(w);
        }
        return edges;
    }

}

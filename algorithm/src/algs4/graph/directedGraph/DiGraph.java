package algs4.graph.directedGraph;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 有向图
 */
public class DiGraph {
    private final int V; //顶点的个数
    private int E; //边的数量
    private ArrayList<Integer>[] adj; //邻接表

    public DiGraph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int v = 0; v < V; v++) adj[v] = new ArrayList<>();
    }

    public DiGraph(Scanner in, int V) {
        this(V);
        int e = in.nextInt();
        for (int i = 0; i < e; i++) {
            int v = in.nextInt();
            int w = in.nextInt();
            addEdges(v, w);
        }
    }

    public void addEdges(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 将图反转
     *
     * @return
     */
    public DiGraph reverse() {
        DiGraph R = new DiGraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.adj[w].add(v);
            }
        }
        return R;
    }
}

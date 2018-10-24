package algs4.graph.undirectedGraph;

/**
 * 判断图是不是二分图
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean TwoColorable = true;

    public TwoColor(Graph graph) {
        marked = new boolean[graph.getV()];
        color = new boolean[graph.getV()];
        for (int v = 0; v < graph.getV(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(graph, w);
            } else if (color[w] == color[v]) TwoColorable = true;
        }
    }

    public boolean isBipartite() {
        return TwoColorable;
    }
}

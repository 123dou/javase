package algs4.graph.undirectedGraph;

/**
 *
 */
public class Search {
    private boolean marked[];
    private int count; //

    public Search(Graph graph, int s) {
        marked = new boolean[graph.getV()];
        dfs(graph, s);
    }

    /**
     * 深度搜索,标记所有与v连通的顶点和数量
     *
     * @param graph
     * @param v
     */
    private void dfs(Graph graph, int v) {
        marked[v] = true;
        count++;
        for (int w : graph.adj(v))
            if (!marked[w]) dfs(graph, w);
    }

    /**
     * 返回跟s连通的顶点数
     *
     * @return
     */
    public int getCount() {
        return count;
    }

}

package algs4.graph.undirectedGraph;

/**
 * 处理一幅图的连通分量, 构造方法接受一幅图,可以找出该图的所有连通分量
 */
public class CC {
    private boolean[] marked;
    private int[] id; //连通分量的编号
    private int count; //连通分量的数量

    /**
     * 找出所有的连通分量
     *
     * @param graph
     */
    public CC(Graph graph) {
        marked = new boolean[graph.getV()];
        id = new int[graph.getV()];
        for (int i = 0; i < graph.getV(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }
    }

    /**
     * 找出graph中v的所有连通分量
     *
     * @param graph
     * @param v
     */
    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    /**
     * 两个结点是否连接
     *
     * @param v
     * @param w
     * @return
     */
    public boolean connect(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * 返回顶点v的连通分量
     *
     * @param v
     * @return
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * 返回连通分量的总数
     *
     * @return
     */
    public int getCount() {
        return count;
    }

}

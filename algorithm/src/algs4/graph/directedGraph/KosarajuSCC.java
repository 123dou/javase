package algs4.graph.directedGraph;

/**
 * 计算强连通分量
 */
public class KosarajuSCC {
    private boolean[] marked; //已访问过的顶点
    private int[] id; //强连通分量的标识符
    private int count; //强连通分量的数量

    public KosarajuSCC(DiGraph diGraph) {
        marked = new boolean[diGraph.getV()];
        id = new int[diGraph.getV()];
        DiGraph reverse = diGraph.reverse();
        DepthFirstOrder order = new DepthFirstOrder(reverse);
        for (int s : order.reversePost()) {
            dfs(diGraph, s);
            count++;
        }
    }


    private void dfs(DiGraph diGraph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : diGraph.adj(v))
            if (!marked[w])
                dfs(diGraph, w);
    }

    public boolean stronglyConnect(int v, int w) {
        return id[v] == id[w];
    }

    public int getCount() {
        return count;
    }
}

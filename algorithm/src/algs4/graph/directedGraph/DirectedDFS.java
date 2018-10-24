package algs4.graph.directedGraph;

/**
 * 判断给定的一个定点或一组顶点的与其他顶点的可达性
 */
public class DirectedDFS {
    private boolean[] marked;
    private int count; //记录的从源点可达的顶点数量

    public DirectedDFS(DiGraph diGraph, int s) {
        marked = new boolean[diGraph.getV()];
        dfs(diGraph, s);
    }

    public DirectedDFS(DiGraph diGraph, Iterable<Integer> source) {
        marked = new boolean[diGraph.getV()];
        for (int s : source) {
            if (!marked[s]) dfs(diGraph, s);
        }
    }

    public boolean isAccessibility(int v) {
        return marked[v];
    }


    private void dfs(DiGraph diGraph, int v) {
        marked[v] = true;
        count++;
        for (int w : diGraph.adj(v))
            if (!marked[w]) dfs(diGraph, w);
    }

    /**
     * 返回从源点可达的顶点数量
     *
     * @return
     */
    private int getCount() {
        return count;
    }

    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
            }
        }
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}

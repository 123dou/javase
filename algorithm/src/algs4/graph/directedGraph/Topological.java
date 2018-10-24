package algs4.graph.directedGraph;

public class Topological {
    private Iterable<Integer> order; //顶点的逆后序排列

    public Topological(DiGraph diGraph) {
        DirectedCycle cycleFinder = new DirectedCycle(diGraph);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(diGraph);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}

package algs4.graph.directedGraph;

import java.util.LinkedList;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgesTo;
    private boolean[] onStack;
    private LinkedList<Integer> cycle;

    public DirectedCycle(DiGraph diGraph) {
        marked = new boolean[diGraph.getV()];
        edgesTo = new int[diGraph.getV()];
        onStack = new boolean[diGraph.getV()];
        for (int s = 0; s < diGraph.getV(); s++)
            if (!marked[s]) dfs(diGraph, s);
    }


    private void dfs(DiGraph diGraph, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : diGraph.adj(v)) {
            if (this.hasCycle()) return;
            else if (!marked[v]) {
                edgesTo[w] = v;
                dfs(diGraph, w);
            } else if (onStack[w]) {
                cycle = new LinkedList<>();
                for (int x = v; v != w; x = edgesTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
            onStack[v] = false;
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}

package algs4.string.regex;

import algs4.graph.directedGraph.DiGraph;
import algs4.graph.directedGraph.DirectedDFS;

import java.util.LinkedList;

public class NFA {
    private char[] re; //匹配转换
    private DiGraph graph; //epsilon转换
    private int M; //装态数量


    public NFA(String regexp) {
        LinkedList<Integer> ops = new LinkedList<>();
        re = regexp.toCharArray();
        M = regexp.length();
        graph = new DiGraph(M + 1);
        for (int i = 0; i < M; i++) {
            int lp = i;
            if (re[i] == '(' || re[i] == '|') ops.push(i);
            else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    graph.addEdges(lp, or + 1);
                    graph.addEdges(or, i);
                } else lp = or;
            }
            if (i < M - 1 && re[i + 1] == '*') {
                graph.addEdges(lp, i + 1);
                graph.addEdges(i + 1, lp);
            }
            if (re[i] == '(' || re[i] == '*' || re[i] == ')') graph.addEdges(i, i + 1);
        }
        if (ops.size() != 0)
            throw new IllegalArgumentException();
    }


    public boolean recognizes(String txt) {
        LinkedList<Integer> pc = new LinkedList<>();
        DirectedDFS dfs = new DirectedDFS(graph, 0);
        for (int v = 0; v < graph.getV(); v++)
            if (dfs.isAccessibility(v)) pc.add(v); //和起点v连通的集合
        for (int i = 0; i < txt.length(); i++) {
            LinkedList<Integer> match = new LinkedList<>();
            for (int v : pc) {
                if (v < M)
                    if (re[v] == txt.charAt(i) || re[v] == '.') match.add(v + 1);
            }
            pc = new LinkedList<>();
            dfs = new DirectedDFS(graph, match);
            for (int v = 0; v < graph.getV(); v++)
                if (dfs.isAccessibility(v)) pc.add(v);
        }
        for (int v : pc)
            if (v == M) return true;
        return false;
    }
}

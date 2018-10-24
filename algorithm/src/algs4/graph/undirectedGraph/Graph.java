package algs4.graph.undirectedGraph;

import javafx.scene.shape.VLineTo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 无向图:用邻接表来表示
 */
public class Graph {
    private final int V; //顶点的数量
    private int E; //边的数量
    private ArrayList<Integer>[] adj; //邻接表

    Graph(int V) {
        this.V = V;
        adj = new ArrayList[V]; //创建邻接表
        //初始化邻接表
        for (int v = 0; v < V; v++) adj[v] = new ArrayList<>();
    }

    /**
     * 从一个标准输入流读入一幅图:这图的描述应该是这样的
     * 5 //第一行表示一共有多少条边
     * 以下5行表示5条边:每一行输入两个数字,表示两个该顶点是连接的
     * 1 2
     * 2 3
     * 4 5
     * <p>
     * 5 6
     * 7 8
     *
     * @param in
     * @param V  该图的顶点总数,不能初始化之后不能变了
     */
    Graph(Scanner in, int V) {
        this(V);
        int E = in.nextInt();
        for (int e = 0; e < E; e++) {
            int v = in.nextInt();
            int w = in.nextInt();
            addEdge(v, w);
        }
    }

    /**
     * 添加一条边
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * 返回一个顶点的所有边
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 返回该图的边的总数
     *
     * @return
     */
    public int getE() {
        return E;
    }

    /**
     * 返回顶点的总数
     *
     * @return
     */
    public int getV() {

        return V;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(V + " vertices, " + E + " edges\n");
        for (int v = 0; v < V; v++) {
            sb.append(v).append(" : ");
            for (int w : adj(v))
                sb.append(w).append(" ");
            sb.append("\n");
        }
        return sb.toString();
    }
}

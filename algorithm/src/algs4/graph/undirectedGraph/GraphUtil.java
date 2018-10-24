package algs4.graph.undirectedGraph;

public class GraphUtil {
    /**
     * 返回某个顶点的度
     *
     * @param graph
     * @param V
     * @return
     */
    public static int degree(Graph graph, int V) {
        int degree = 0;
        for (int w : graph.adj(V)) degree++;
        return degree;
    }

    /**
     * 计算一幅图的最大度数
     *
     * @param graph
     * @return
     */
    public static int maxDegree(Graph graph) {
        int maxDegree = 0;
        for (int v = 0; v < graph.getV(); v++)
            maxDegree = Math.max(maxDegree, degree(graph, v));
        return maxDegree;
    }

    /**
     * 获取该图的平均度数
     *
     * @param graph
     * @return
     */
    public static double aveDegree(Graph graph) {
        return 2.0 * graph.getE() / graph.getE();
    }

    /**
     * 计算自还的个数
     *
     * @param graph
     * @return
     */
    public static int numberOfSelfLoops(Graph graph) {
        int count = 0;
        for (int v = 0; v < graph.getV(); v++)
            for (int w : graph.adj(v))
                if (v == w) count++;
        return count / 2; //每条边被计过两次
    }
}

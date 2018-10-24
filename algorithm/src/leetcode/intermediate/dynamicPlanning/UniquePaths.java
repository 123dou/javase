package leetcode.intermediate.dynamicPlanning;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * <p>
 * <p>
 * 例如，上图是一个 7 x 3 的网格。有多少可能的路径？
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * <p>
 * 输入: m = 7, n = 3
 * 输出: 28
 */
public class UniquePaths {
    public static void main(String[] args) {
        int m = 4;
        int n = 7;
        int i = uniquePaths(m, n);
        System.out.println(i);
    }

    /**
     * f(m,n) = f(n-1,m) + f(n,m-1);
     * 初始条件:f(1,n) = 1, f(m,1) = 1;
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        if (n == 1 || m == 1) return 1;
        int[][] record = new int[m][n];
        record[0][0] = 0;
        for (int i = 1; i < m; i++) {
            record[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            record[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                record[i][j] = record[i - 1][j] + record[i][j - 1];
            }
        }
        return record[m - 1][n - 1];
    }
}

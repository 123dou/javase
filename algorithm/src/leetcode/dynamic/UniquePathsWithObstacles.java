package leetcode.dynamic;

import java.util.jar.JarEntry;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * <p>
 * <p>
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 */
public class UniquePathsWithObstacles {
    public static void main(String[] args) {

    }

    /**
     * 暴力搜索,超时是必然的...
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0][0] == 1
                || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1) return 0;
        uniquePathWithObstacles(obstacleGrid, 0, 0);
        return paths;
    }

    static int paths = 0;

    public static void uniquePathWithObstacles(int[][] obstacleGrid, int m, int n) {
        if (m == obstacleGrid.length || n == obstacleGrid[0].length) return;
        if (m == obstacleGrid.length - 1 && n == obstacleGrid[0].length - 1) {
            paths++;
            return;
        }
        if (obstacleGrid[m][n] == 1) return;
        uniquePathWithObstacles(obstacleGrid, m + 1, n);
        uniquePathWithObstacles(obstacleGrid, m, n + 1);
    }

    /**
     * dp[i][j] = dp[i-1][j]+dp[i][j-1]
     * 当obstacleGrid[i-1][j]跟obstacleGrid[i][j-1]都为0时,dp[i][j] = dp[i-1][j]+dp[i][j-1]
     * 当obstacleGrid[i-][j]跟obstacleGrid[i][j-1]都为1时,dp[i][j] = 0
     * 其它情况,dp[i][j]等于obstacleGrid[i-1][j]跟obstacleGrid[i][j-1]中等于0的那个
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0][0] == 1
                || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1) return 0;
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            if (obstacleGrid[i][0] == 0) dp[i][0] = 1;
            else break;
        }
        for (int i = 0; i < obstacleGrid[0].length; i++) {
            if (obstacleGrid[0][i] == 0) dp[0][i] = 1;
            else break;
        }
        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i - 1][j] == 0 && obstacleGrid[i][j - 1] == 0) dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                else if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] == 1) ;
                else if (obstacleGrid[i - 1][j] == 0) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }
}

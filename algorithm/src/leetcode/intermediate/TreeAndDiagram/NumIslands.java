package leetcode.intermediate.TreeAndDiagram;

public class NumIslands {
    public static void main(String[] args) {

    }

    public static int numIslands(char[][] grid) {
        if (grid == null) return 0;
        boolean[][] marked = new boolean[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            marked[i] = new boolean[grid[i].length];
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!marked[i][j] && grid[i][j] == '1') {
                    setIslandCount(grid, marked, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 将一个岛屿里面的所有一都设置为true
     *
     * @param grid
     * @param marked
     * @param i
     * @param j
     */
    public static void setIslandCount(char[][] grid, boolean[][] marked, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == '0' || marked[i][j]) return;
        marked[i][j] = true;
        setIslandCount(grid, marked, i, j + 1);
        setIslandCount(grid, marked, i, j - 1);
        setIslandCount(grid, marked, i + 1, j);
        setIslandCount(grid, marked, i - 1, j);
    }
}

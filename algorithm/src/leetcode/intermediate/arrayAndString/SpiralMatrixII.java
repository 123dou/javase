package leetcode.intermediate.arrayAndString;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrixII {
    public static void main(String[] args) {

    }


    public int[][] generateMatrix(int n) {
        if (n <= 0) return null;
        int[][] res = new int[n][n];
        int[] p = {1};
        int row = n - 1;
        int col = n - 1;
        int i = 0, j = 0;
        while (i <= row && j <= col) {
            buildLoop(res, i++, j++, row--, col--, p);
        }
        return res;
    }

    private void buildLoop(int[][] arr, int i, int j, int row, int col, int[] p) {
        for (int n = j; n <= col; n++) arr[i][n] = p[0]++;
        for (int n = i + 1; n <= col; n++) arr[n][col] = p[0]++;
        for (int n = col - 1; n >= j; n--) arr[row][n] = p[0]++;
        for (int n = row - 1; n >= i + 1; n--) arr[n][j] = p[0]++;
    }


}

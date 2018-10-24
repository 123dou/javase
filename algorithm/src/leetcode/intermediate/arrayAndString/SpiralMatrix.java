package leetcode.intermediate.arrayAndString;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public static void main(String[] args) {

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return list;
        int i, j;
        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        for (i = 0, j = 0; i <= row && j <= col; ) {
            addLoop(matrix, i++, j++, row--, col--, list);
        }
        return list;
    }


    private void addLoop(int[][] arr, int i, int j, int row, int col, List<Integer> list) {
        for (int n = j; n <= col; n++) list.add(arr[i][n]); //第一行
        for (int n = i + 1; n <= row; n++) list.add(arr[n][col]); //最后一列
        if (i != row)
            for (int n = col - 1; n >= j; n--) list.add(arr[row][n]); //最后一行
        if (j != col)
            for (int n = row - 1; n >= i + 1; n--) list.add(arr[n][j]); //第一列
    }
}

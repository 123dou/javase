package leetcode.intermediate.arrayAndString;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 * <p>
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 */
public class SearchA2dMatrix {
    public static void main(String[] args) {

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        for (int i = matrix.length - 1, j = 0; i >= 0 && j < matrix[i].length; ) {
            if (target == matrix[i][j]) return true;
            if (target > matrix[i][j]) j++;
            else i--;
        }
        return false;
    }
}

package leetcode.intermediate.sortAndSearch;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * <p>
 * 给定 target = 20，返回 false。
 */
public class SearchMatrix {
    public static void main(String[] args) {
        //int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int target = 5;
        boolean b = searchMatrix3(matrix, target);
        System.out.println(b);
    }

    /**
     * 完美的利用了矩阵的特性
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix3(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return false;
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[row].length) {
            if (target == matrix[row][col]) return true;
            if (target < matrix[row][col]) row--;
            else col++;
        }
        return false;
    }


    /**
     * 优化了一下的二叉搜索,还不是最好的
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int low = 0;
        int high = matrix.length - 1;
        int mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (matrix[mid] != null && matrix[mid].length != 0) {
                if (matrix[mid][0] == target) return true;
                if (target < matrix[mid][0]) high = mid - 1;
                else low = mid + 1;
            } else return false;
        }
        for (int i = 0; i <= high; i++) {
            if (matrix[i] != null && matrix.length != 0) {
                if (BSearch(matrix[i], target)) return true;
            }
        }
        return false;
    }


    /**
     * 对每行或每列使用二叉搜索,算法不算高效
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] != null && matrix.length != 0) {
                if (BSearch(matrix[i], target)) return true;
            }
        }
        return false;
    }

    public static boolean BSearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] == target) return true;
            if (target > array[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }
}

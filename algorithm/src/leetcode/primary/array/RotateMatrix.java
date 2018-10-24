package leetcode.primary.array;

public class RotateMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        rotate(matrix);
    }

    /**
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        int row;
        int col;
        int len = length / 2;
        for (int k = 0; k < len; k++) {
            for (int i = k; i < length - k - 1; i++) {
                int l = 0;
                row = k;
                col = i;
                int value = matrix[row][col];
                while (l < 4) {
                    int[] ints = nextPos(row, col, length - 1);
                    row = ints[0];
                    col = ints[1];
                    int temp = matrix[row][col];
                    matrix[row][col] = value;
                    value = temp;
                    l++;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 当前位置旋转之后的位置
     *
     * @param row
     * @param col
     * @param length
     * @return
     */
    public static int[] nextPos(int row, int col, int length) {
        return new int[]{col, length - row};
    }

}

package swordToOffer;

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵：
 * 1 2 3 4
 * 5 6 7 8
 * 9 10 11 12
 * 13 14 15 16
 * 则依次打印出数字
 * 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class PxrintMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1},
                {5},
                {9},
                {13}
        };
        PxrintMatrix p = new PxrintMatrix();
        ArrayList<Integer> list = p.printMatrix(matrix);
        System.out.println(list);
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return list;
        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        int i = 0, j = 0;
        while (i <= row && j <= col) {
            if (row - i < 0 || col - j < 0) break;
            printMatrix(matrix, i++, j++, row--, col--, list);
        }

        return list;
    }

    private ArrayList<Integer> printMatrix(int[][] matrix, int i, int j, int row, int col, ArrayList<Integer> list) {
        for (int n = i; n <= col; n++) list.add(matrix[i][n]);
        for (int n = i + 1; n <= row; n++) list.add(matrix[n][col]);
        for (int n = col - 1; n >= j && row != i; n--) list.add(matrix[row][n]);
        for (int n = row - 1; n >= i + 1 && col != j; n--) list.add(matrix[n][j]);
        return list;
    }
}

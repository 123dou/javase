package testDemo;

import java.util.*;

public class MainTest {

    public static void main(String[] args) {
        char[] arr = {'A', 'B', 'C', 'E', 'S', 'F', 'C', 'S', 'A', 'D', 'E', 'E'};
        char[] str = {'A', 'B', 'C', 'C', 'E', 'D'};
        MainTest m = new MainTest();
        boolean b = m.hasPath(arr, 3, 4, str);
        System.out.println(b);
    }

    private char[][] arrs;
    private int rows;
    private int cols;
    private char[] str;
    private boolean[][] marked;

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || matrix.length == 0 || str.length > matrix.length) return false;
        arrs = new char[rows][cols];
        marked = new boolean[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.str = str;
        for (int i = 0, j = 0; i < matrix.length; i += cols, j++) {
            for (int k = 0; k < cols; k++) {
                arrs[j][k] = matrix[k + i];
            }
        }
        boolean res = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res = hasPath(i, j, 0);
                if (res) return true;
            }
        }
        return false;
    }


    private boolean hasPath(int i, int j, int index) {
        if (index == str.length) return true;
        if (i < 0 || j < 0 || i >= rows || j >= cols || marked[i][j] || arrs[i][j] != str[index]) return false;
        marked[i][j] = true;
        boolean res = hasPath(i + 1, j, index + 1)
                || hasPath(i, j + 1, index + 1)
                || hasPath(i - 1, j, index + 1)
                || hasPath(i, j - 1, index + 1);
        marked[i][j] = false;
        return res;

    }


}
